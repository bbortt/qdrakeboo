// @flow
import {call, select, takeLatest} from 'redux-saga/effects'

import axios from 'axios'

import type {RequestUserInfoAction} from '../actions'
import {REQUEST_USER_INFO} from '../actions'

import {getToken} from '../facade/session.facade'

function* requestUserInfo(action: RequestUserInfoAction) {
  const token = yield select(getToken)

  const response = yield call(axios.get, 'http://localhost:8081/', {
    headers: {
      'Authorization': `${token.token_type} ${token.access_token}`
    }
  })
}

export function* requestUserInfoSaga(): Iterable<any> {
  yield takeLatest(REQUEST_USER_INFO, requestUserInfo)
}
