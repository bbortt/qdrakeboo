import React from 'react'

import cookies from 'js-cookie'
import {TOKEN_COOKIE_NAME} from './security.constants'

import getAuthenticationToken from './get-authentication-token'

import {requestSession, setToken} from '../../state/actions'

export default (Component: React.Component): React.Component => {
  return class AuthenticatedPage extends React.Component {
    static displayName = `withAuthenticationOnly(${Component.displayName
    || Component.name
    || 'AuthenticatedPage'})`

    static async getInitialProps({ctx}) {
      const {res, store} = ctx

      const token = getAuthenticationToken(ctx)

      if (!token) {
        store.dispatch(requestSession(res))
      }

      let pageProps = {}
      if (Component.getInitialProps) {
        pageProps = await Component.getInitialProps(props)
      }

      return {token, ...pageProps}
    }

    constructor(props) {
      super(props)

      const token = cookies.get(TOKEN_COOKIE_NAME)

      if (token) {
        props.dispatch(setToken(JSON.parse(token)))
      }
    }

    render() {
      return <Component {...this.props} />
    }
  }
}
