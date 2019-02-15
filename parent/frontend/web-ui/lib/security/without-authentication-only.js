// @flow
import React from 'react'

import Router from 'next/router'

import getAuthenticationToken from './get-authentication-token'

const authenticatedEntryPoint = '/home'

export default (Component: React.Component): React.Component => {
  return class AnonymousPage extends React.Component {
    static displayName = `withoutAuthenticationOnly(${Component.displayName
    || Component.name
    || 'AnonymousPage'})`

    static async getInitialProps({ctx}) {
      const {res} = ctx

      const token = getAuthenticationToken(ctx)

      if (token) {
        if (res) {
          res.redirect(authenticatedEntryPoint)
        } else {
          Router.push(authenticatedEntryPoint)
        }
      }

      let pageProps = {}
      if (Component.getInitialProps) {
        pageProps = await Component.getInitialProps(props)
      }

      return {...pageProps}
    }

    render() {
      return <Component {...this.props} />
    }
  }
}
