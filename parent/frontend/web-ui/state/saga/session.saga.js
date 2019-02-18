// @flow
import Router from 'next/router';
import getConfig from 'next/config'

import {call, select, takeLatest} from 'redux-saga/effects'

import axios from 'axios'

import type {RequestSessionAction, RequestUserInfoAction} from '../actions'
import {REQUEST_SESSION, REQUEST_USER_INFO} from '../actions'

import {getToken} from '../facade/session.facade'

const {publicRuntimeConfig} = getConfig()

const sessionEndpoint = '/session'

function* redirect(url: string, response: any | null) {
  if (response) {
    response.redirect(url)
  } else {
    Router.push(url)
  }
}

function* requestSession(action: RequestSessionAction) {
  yield redirect(sessionEndpoint, action.response)
}

export function* requestSessionSaga(): Iterable<any> {
  yield takeLatest(REQUEST_SESSION, requestSession)
}

function* requestUserInfo(action: RequestUserInfoAction) {
  const token = yield select(getToken)

  try {
    const response = yield call(axios.get, `${publicRuntimeConfig.apiServerUrl}/user`, {
      headers: {
        'Authorization': `${token.token_type} ${token.access_token}`
      }
    })
  } catch (error) {
    // TODO: Dispatch error
  }
}

export function* requestUserInfoSaga(): Iterable<any> {
  yield takeLatest(REQUEST_USER_INFO, requestUserInfo)
}
