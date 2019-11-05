// @flow
import Router from 'next/router'

import axios from 'axios'

import type { WellKnownType } from '../domain/WellKnown.type'

const stub = {
  auth0: {
    domain: '',
    clientId: '',
    callbackUrl: 'http://localhost:3000/app',
  },
  api: {
    audience: '',
    url: 'http://localhost:8080',
  },
  logoutRedirect: 'http://localhost:3000/goodbye',
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

export default async (): Promise<{ wellKnown: WellKnownType }> => {
  if (process.env.NODE_ENV !== 'development' && typeof window !== 'undefined') {
    return axios.get('/.well-known.json').then(response => {
      wellKnown = response.data
      return Promise.resolve({ wellKnown })
    })
  }

  // $FlowFixMe
  const context = require.context(
    '../config',
    false,
    /^\.\/well-known\.json$/
  )
  context.keys().forEach(key => {
    wellKnown = context(key)
  })

  return Promise.resolve({ wellKnown })
}
