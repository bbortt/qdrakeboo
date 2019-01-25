import getConfig from 'next/config'

import es6promise from 'es6-promise'

import {takeEvery} from 'redux-saga/effects'
import fetch from 'isomorphic-unfetch'

import {AuthenticationTypes} from '../actions'
import {authenticationFailed, authenticationSucceed} from '../facade'

es6promise.polyfill()

const {publicRuntimeConfig} = getConfig()

function* fetchAuthentication(action) {
  fetch(`${publicRuntimeConfig.authorizationServerUrl}/user`).then((response) => {
    authenticationSucceed()
  }).catch((error) => {
    authenticationFailed()
  })
}

export function* authenticationSaga() {
  yield takeEvery(AuthenticationTypes.AUTHENTICATION_REQUEST, fetchAuthentication)
}
