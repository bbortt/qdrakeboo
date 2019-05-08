// @flow
import React from 'react';

import isAuthenticated from './isAuthenticated'

import contextAwareRedirect from '../next/contextAwareRedirect'

export default function withAuthenticationOnly(Component: any): any {
  return class AuthenticatedPage extends React.Component<any> {
    static displayName = `AuthenticatedPage(${Component.displayName
    || Component.name
    || 'AuthenticatedPage'})`

    static async getInitialProps({ctx}) {
      const {isServer, req, res} = ctx

      if (!await isAuthenticated({isServer, req, res})) {
        return contextAwareRedirect('/', {isServer, req, res})
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
