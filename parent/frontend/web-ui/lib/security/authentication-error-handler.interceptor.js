// @flow
import Router from 'next/router'

import {Store} from 'redux';

import axios from 'axios'

import {getToken} from '../../state/facade/session.facade';

import {getDateWithTimezoneOffset} from '../date/get-date-with-timezone-offset'

const addRequestInterceptor = (store: Store) => {
  axios.interceptors.request.use(function (config) {
    return config;
  }, function (error) {
    return Promise.reject(error);
  })
}

const addResponseInterceptor = (store: Store) => {
  axios.interceptors.response.use(function (response) {
    return response;
  }, function (error) {
    const token = getToken(store.getState())

    if (error.response.status === 401 && token.expires
        < getDateWithTimezoneOffset().getTime()) {
      Router.push(`/session/renew?redirect=${Router.rout}`)
    } else {
      return Promise.reject(error);
    }
  })
}

export default (store: Store) => {
  addRequestInterceptor(store)
  addResponseInterceptor(store)
}
