import {call, put, takeEvery} from 'redux-saga/effects'

import axios from 'axios'

import {AuthenticationFailedAction, AuthenticationSucceedAction, AuthenticationTypes} from '../actions'

import getConfig from 'next/config'

const {publicRuntimeConfig} = getConfig()

function* fetchAuthentication(action) {
  try {
    const response = yield call(axios.get, `${action.payload.isServer ? publicRuntimeConfig.uiServerUrl : ''}/auth`)
    yield put(AuthenticationSucceedAction(response.data))
  } catch (e) {
    yield put(AuthenticationFailedAction())
  }
}

export function* authenticationSaga() {
  yield takeEvery(AuthenticationTypes.AUTHENTICATION_REQUEST, fetchAuthentication)
}
