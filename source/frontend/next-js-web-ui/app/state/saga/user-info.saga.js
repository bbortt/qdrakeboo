// @flow
import { call, put, takeLatest } from 'redux-saga/effects'

import axios from 'axios'

import type {
  CompleteUserInfoAction,
  RequestPermissionsAction,
} from '../action'

import {
  COMPLETE_USER_INFO,
  REQUEST_PERMISSIONS,
  requestPermissions as requestPermissionsAction,
  requestPermissionsFailed,
  setPermissions,
  setUserInfo,
} from '../action'

const { API_FORWARD_TO_HEADER_NAME, API_URL, PUBLIC_URL } = process.env

function* completeUserInfo(action: CompleteUserInfoAction) {
  const { nextContext } = action

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

  if (!API_FORWARD_TO_HEADER_NAME) {
    throw new Error('process.env.API_FORWARD_TO_HEADER_NAME not defined!')
  }

  if (!API_URL) {
    throw new Error('process.env.API_URL not defined!')
  }

  requestConfig.headers = {}
  requestConfig.headers[API_FORWARD_TO_HEADER_NAME] = `${API_URL}/userinfo`

  if (action.nextContext.req && action.nextContext.req.headers.cookie) {
    requestConfig.headers.cookie = action.nextContext.req.headers.cookie
  }

  if (!PUBLIC_URL) {
    throw new Error('process.env.PUBLIC_URL not defined!')
  }

  try {
    const response = yield call(axios.get, `${PUBLIC_URL}/api`, requestConfig)

    yield put(setPermissions(response.data.credentials.claims.permissions))
  } catch (error) {
    yield put(requestPermissionsFailed(error))
  }
}

export function* requestPermissionsSaga(): Iterable<any> {
  yield takeLatest(REQUEST_PERMISSIONS, requestPermissions)
}
