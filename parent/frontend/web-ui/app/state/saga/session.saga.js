// @flow
import getConfig from 'next/config'
import contextAwareRedirect from '../../common/next/contextAwareRedirect'

import {call, put, takeLatest} from 'redux-saga/effects'

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
        `${publicRuntimeConfig.publicUrl}/api/microservice/principal`,
        requestConfig)

    yield put(setUserInfo(response.data))
  } catch (error) {
    // TODO: Global axios handler
    if (error.response) {
      switch (error.response.status) {
        case 302:
          contextAwareRedirect(error.response.headers.location,
              action.nextContext)
        case 401:
          if (action.nextContext.req.originalUrl !== '/') {
            contextAwareRedirect('/', action.nextContext)
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
