// @flow
import Router from 'next/router'

import axios from 'axios'

import type { WellKnownType } from '../domain/WellKnown.type'

const stub = {
  auth0: {
    domain: '',
    clientId: '',
    callbackUrl: '',
  },
  api: {
    audience: '',
    url: '',
  },
  logoutRedirect: '',
}

let wellKnown: WellKnownType = stub

export const syncWellKnown = (): WellKnownType => {
  if (wellKnown === stub) {
    Router.push({
      pathname: '/oops.html',
      query: {
        message: 'Something real bad happened!', // TODO: I18n error code
      },
    })
  }

  return wellKnown
}

export default async (): Promise<{ data: WellKnownType }> => {
  if (process.env.NODE_ENV === 'development') {
    // $FlowFixMe
    wellKnown = require('../../public/.well-known.json')
    return Promise.resolve({ data: wellKnown })
  }

  if (typeof window !== 'undefined') {
    return axios.get('/.well-known.json').then(response => {
      wellKnown = response.data
    })
  }

  return Promise.resolve({ data: stub })
}
