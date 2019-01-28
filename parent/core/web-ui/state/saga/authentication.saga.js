import es6promise from 'es6-promise'

import {takeEvery} from 'redux-saga/effects'

import axios from 'axios'

import {AuthenticationTypes} from '../actions'
import {authenticationFailed, authenticationSucceed} from '../facade'

import getConfig from 'next/config'

const {publicRuntimeConfig} = getConfig()

es6promise.polyfill()

function* fetchAuthentication(action) {
  axios.get(`${action.payload.isServer ? publicRuntimeConfig.uiServerUrl : ''}/auth`)
    .then((response) => authenticationSucceed(response.data))
    .catch((error) => authenticationFailed())
}

export function* authenticationSaga() {
  yield takeEvery(AuthenticationTypes.AUTHENTICATION_REQUEST, fetchAuthentication)
}
