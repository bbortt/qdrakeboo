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
    return Promise.resolve({ data: require('../../public/.well-known.json') })
  } else if (typeof window !== 'undefined') {
    return axios.get('/.well-known.json')
  }

  return Promise.resolve({ data: stub })
}
