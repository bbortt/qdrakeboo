// @flow
import {call, put, takeLatest} from 'redux-saga/effects'

import axios from 'axios'

import type {SessionRequestAction} from '../actions';
import {
  SESSION_REQUEST,
  sessionRequestFailed,
  sessionRequestSucceed
} from '../actions'

import getConfig from 'next/config'

const {publicRuntimeConfig} = getConfig()

function* fetchAuthentication(action: SessionRequestAction) {
  try {
    const response = yield call(axios.get,
        `${action.isServer ? publicRuntimeConfig.uiServerUrl : ''}/auth`)
    yield call(setAuthorization, response.data.token_type,
        response.data.access_token)
    yield put(sessionRequestSucceed(response.data))
  } catch (e) {
    yield put(sessionRequestFailed())
  }
}

function* setAuthorization(type: string, auth: string) {
  axios.defaults.headers.common['Authorization'] = `${type}: ${auth}`
}

export default function* sessionSaga(): Iterable<any> {
  yield takeLatest(SESSION_REQUEST, fetchAuthentication)
}
