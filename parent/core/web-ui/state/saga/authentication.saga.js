import {all, call, put, takeLatest} from 'redux-saga/effects'

import axios from 'axios'

import {
  AuthenticationActionTypes,
  AuthenticationFailedAction,
  AuthenticationSucceedAction
} from '../actions'

import getConfig from 'next/config'

const {publicRuntimeConfig} = getConfig()

function* fetchAuthentication(action) {
  try {
    const response = yield call(axios.get,
        `${action.payload.isServer ? publicRuntimeConfig.uiServerUrl
            : ''}/auth`)
    yield put(AuthenticationSucceedAction(response.data))
  } catch (e) {
    yield put(AuthenticationFailedAction())
  }
}

export default function* authenticationSaga() {
  yield all([
    takeLatest(AuthenticationActionTypes.AUTHENTICATION_REQUEST,
        fetchAuthentication)
  ])
}
