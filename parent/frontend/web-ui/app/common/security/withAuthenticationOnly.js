// @flow
import React from 'react';

export default function withAuthenticationOnly(Component: React.Component): React.Component {
  return class AuthenticatedPage extends React.Component {
    static displayName = `withAuthenticationOnly(${Component.displayName
    || Component.name
    || 'AuthenticatedPage'})`

    static async getInitialProps({ctx}) {
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
