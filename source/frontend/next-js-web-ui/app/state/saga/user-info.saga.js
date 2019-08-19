// @flow
import getConfig from 'next/config'

import { all, call, fork, put, takeLatest } from 'redux-saga/effects'

import axios from 'axios'

import type {
  CompleteUserInfoAction,
  RequestPermissionsAction,
  RequestPermissionsFailedAction,
} from '../action'
import {
  addErrorAlert,
  COMPLETE_USER_INFO,
  REQUEST_PERMISSIONS,
  REQUEST_PERMISSIONS_FAILED,
  requestPermissions as requestPermissionsAction,
  requestPermissionsFailed as requestPermissionsFailedAction,
  setPermissions,
  setUserInfo,
} from '../action'

const { publicRuntimeConfig } = getConfig()

function* completeUserInfo(action: CompleteUserInfoAction) {
  const { nextContext } = action

  if (nextContext.isServer && nextContext.query.userInfo) {
    yield put(setUserInfo(nextContext.query.userInfo))
    yield put(requestPermissionsAction(nextContext))
  }
}

function* completeUserInfoSaga(): Iterable<any> {
  yield takeLatest(COMPLETE_USER_INFO, completeUserInfo)
}

function* requestPermissions(action: RequestPermissionsAction) {
  const requestConfig = {}

  requestConfig.headers = {}
  requestConfig.headers[
    publicRuntimeConfig.apiForwardToHeaderName
  ] = `${publicRuntimeConfig.apiUrl}/userinfo`

  if (action.nextContext.req && action.nextContext.req.headers.cookie) {
    requestConfig.headers.cookie = action.nextContext.req.headers.cookie
  }

  try {
    const response = yield call(
      axios.get,
      `${publicRuntimeConfig.publicUrl}/api`,
      requestConfig,
    )

    console.log('response: ', response)

    yield put(setPermissions(response.data.credentials.claims.permissions))
  } catch (error) {
    yield put(requestPermissionsFailedAction(error))

    const { response } = error

    yield put(
      addErrorAlert(
        `Failed to load the account information: ${response.statusText}! Please refresh the page to retry.`,
        'Fatal error!',
      ),
    )
  }
}

function* requestPermissionsSaga(): Iterable<any> {
  yield takeLatest(REQUEST_PERMISSIONS, requestPermissions)
}

export default function* userInfoSaga(): Iterable<any> {
  yield all([completeUserInfoSaga(), requestPermissionsSaga()])
}
