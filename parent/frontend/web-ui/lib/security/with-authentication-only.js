import React from 'react'

import Router from 'next/router'

import cookies from 'js-cookie'
import {TOKEN_COOKIE_NAME} from './security.constants'

import getAuthenticationToken from './get-authentication-token'

import {setToken} from '../../state/actions'

const loginEndpoint = '/session'

export default (Component: React.Component): React.Component => {
  return class AuthenticatedPage extends React.Component {
    static displayName = `withAuthenticationOnly(${Component.displayName
    || Component.name
    || 'AuthenticatedPage'})`

    static async getInitialProps({ctx}) {
      const {res} = ctx

      const token = getAuthenticationToken(ctx)

      if (!token) {
        if (res) {
          res.redirect(loginEndpoint)
        } else {
          Router.push(loginEndpoint)
        }
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
