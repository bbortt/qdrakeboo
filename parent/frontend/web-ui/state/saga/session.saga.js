// @flow
import Router from 'next/router'
import getConfig from 'next/config'

import {call, takeLatest} from 'redux-saga/effects'

import axios from 'axios'

import type {RequestUserInfoAction} from '../actions'
import {REQUEST_USER_INFO} from '../actions'

const {publicRuntimeConfig} = getConfig()

function* requestUserInfo(action: RequestUserInfoAction) {
  const requestConfig = {}

  if (action.nextContext.isServer) {
    requestConfig.headers = {
      cookie: action.nextContext.req.headers.cookie
    }
  }

  try {
    const response = yield call(axios.get, `${publicRuntimeConfig.publicApiUrl}/microservice/principal`, requestConfig)

    console.log('response received: ', response)
  } catch (error) {
    switch (error.response.status) {
      case 302:
        if (action.nextContext.res) {
          action.nextContext.res.redirect(error.response.headers.location)
        } else {
          Router.push(error.response.headers.location)
        }
    }
  }
}

export function* requestUserInfoSaga(): Iterable<any> {
  yield takeLatest(REQUEST_USER_INFO, requestUserInfo)
}
