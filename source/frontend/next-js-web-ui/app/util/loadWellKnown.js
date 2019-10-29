// @flow
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

export default () => {
  if (process.env.NODE_ENV === 'development') {
    // $FlowFixMe
    return Promise.resolve({ data: require('../../public/.well-known.json') })
  }

  if (typeof window !== 'undefined') {
    return axios.get('/.well-known.json')
  }

  return Promise.resolve({ data: stub })
}
