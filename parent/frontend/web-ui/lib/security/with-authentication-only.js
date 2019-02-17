// @flow
import React from 'react'

import cookies from 'js-cookie'
import {TOKEN_COOKIE_NAME} from './security.constants'

import getAuthenticationToken from './get-authentication-token'

import {requestSession, setToken} from '../../state/actions'

// $FlowFixMe
export default (Component: React.Component) => {
  return class AuthenticatedPage extends React.Component<AuthenticatedPage.propTypes> {
    static displayName = `withAuthenticationOnly(${Component.displayName
    || Component.name
    || 'AuthenticatedPage'})`

    constructor(props: AuthenticatedPage.propTypes) {
      super(props)

      const token = cookies.get(TOKEN_COOKIE_NAME)

      if (token) {
        props.dispatch(setToken(JSON.parse(token)))
      }
    }

    static async getInitialProps({ctx}: any) {
      const {res, store} = ctx

      const token = getAuthenticationToken(ctx)

      if (!token) {
        store.dispatch(requestSession(res))
      }

      let pageProps = {}
      if (Component.getInitialProps) {
        pageProps = await Component.getInitialProps({ctx})
      }

      return {token, ...pageProps}
    }

    render() {
      return <Component {...this.props} />
    }
  }
}
