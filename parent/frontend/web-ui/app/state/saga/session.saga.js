// @flow
import Router from 'next/router'
import getConfig from 'next/config'

import {put, call, takeLatest} from 'redux-saga/effects'

import axios from 'axios'

import type {RequestUserInfoAction} from '../actions'
import {REQUEST_USER_INFO, setUserInfo} from '../actions'

const {publicRuntimeConfig} = getConfig()

function* requestUserInfo(action: RequestUserInfoAction) {
  const requestConfig = {}

  if (action.nextContext.isServer && action.nextContext.req.headers.cookie) {
    requestConfig.headers = {
      cookie: action.nextContext.req.headers.cookie
    }
  }

  try {
    const response = yield call(axios.get,
        `${publicRuntimeConfig.publicApiUrl}/microservice/principal`,
        requestConfig)

    yield put(setUserInfo(response.data))
  } catch (error) {
    // TODO: Global axios handler
    if (error.response) {
      switch (error.response.status) {
        case 302:
          if (action.nextContext.res) {
            action.nextContext.res.redirect(error.response.headers.location)
          } else {
            Router.push(error.response.headers.location)
          }
      }
    } else {
      // TODO: Unknown error?
    }
  }
}

export function* requestUserInfoSaga(): Iterable<any> {
  yield takeLatest(REQUEST_USER_INFO, requestUserInfo)
}
