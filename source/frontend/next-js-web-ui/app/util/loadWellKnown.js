// @flow
import type { WellKnownType } from '../domain/WellKnown.type'

import axios from 'axios'

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

export const syncWellKnown = (): WellKnownType => wellKnown

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
