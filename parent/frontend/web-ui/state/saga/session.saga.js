// @flow
import getConfig from 'next/config'

import {call, takeLatest} from 'redux-saga/effects'

import axios from 'axios'

import type {RequestUserInfoAction} from '../actions'
import {REQUEST_USER_INFO} from '../actions'

const {publicRuntimeConfig} = getConfig()

function* requestUserInfo(action: RequestUserInfoAction) {
  const response = yield call(axios.get, `${publicRuntimeConfig.publicApiUrl}/account`,)

  console.log('response: ', response)
}

export function* requestUserInfoSaga(): Iterable<any> {
  yield takeLatest(REQUEST_USER_INFO, requestUserInfo)
}
