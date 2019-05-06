// @flow
import React from 'react';

import isAuthenticated from './isAuthenticated'

import contextAwareRedirect from '../next/contextAwareRedirect'

export default function withAuthenticationOnly(Component: React.Component): React.Component {
  return class AuthenticatedPage extends React.Component {
    static displayName = `withAuthenticationOnly(${Component.displayName
    || Component.name
    || 'AuthenticatedPage'})`

    static async getInitialProps({ctx}) {
      const {isServer, req, res, query} = ctx

      if (!await isAuthenticated({isServer, req, res})) {
        contextAwareRedirect('/', {isServer, req, res})
      }

      let pageProps = {}

      if (Component.getInitialProps) {
        pageProps = await Component.getInitialProps({ctx})
      }

      return {...pageProps}
    }

    render() {
      return <Component {...this.props} />
    }
  }
}
