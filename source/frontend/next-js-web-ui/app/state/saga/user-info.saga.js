// @flow
import getConfig from 'next/config'

import {call, put, takeLatest} from 'redux-saga/effects'

import axios from 'axios'

import {API_FORWARD_TO_HEADER_NAME} from '../../../shared/const'

import type {CompleteUserInfoAction, RequestPermissionsAction} from '../action'
import {
  COMPLETE_USER_INFO,
  REQUEST_PERMISSIONS,
  requestPermissions as requestPermissionsAction,
  requestPermissionsFailed,
  setPermissions,
  setUserInfo
} from '../action'

const {publicRuntimeConfig} = getConfig()

function* completeUserInfo(action: CompleteUserInfoAction) {
  const {nextContext} = action;

  if (nextContext.isServer && nextContext.query.userInfo) {
    yield put(setUserInfo(nextContext.query.userInfo))
    yield put(requestPermissionsAction(nextContext))
  }
}

export function* completeUserInfoSaga(): Iterable<any> {
  yield takeLatest(COMPLETE_USER_INFO, completeUserInfo)
}

function* requestPermissions(action: RequestPermissionsAction) {
  const requestConfig = {}

  const audience = process.env.AUTH0_DOMAIN
  if (!audience) {
    yield put(requestPermissionsFailed('No audience present!'))
    return
  }

  requestConfig.headers = {}
  requestConfig.headers[API_FORWARD_TO_HEADER_NAME] = `https://${audience}/userinfo`

  if (action.nextContext.req && action.nextContext.req.headers.cookie) {
    requestConfig.headers.cookie = action.nextContext.req.headers.cookie
  }

  try {
    const response = yield call(axios.get,
        `${publicRuntimeConfig.publicUrl}/api`,
        requestConfig)

    yield put(setPermissions(response.data.credentials.claims.permissions))
  } catch (error) {
    yield put(requestPermissionsFailed(error))
  }
}

export function* requestPermissionsSaga(): Iterable<any> {
  yield takeLatest(REQUEST_PERMISSIONS, requestPermissions)
}
