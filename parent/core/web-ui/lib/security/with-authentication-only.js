import React from 'react';

import {setToken} from '../../state/actions';

const loginEndpoint = '/login'

export default function withAuthenticationOnly(Component: React.Component): React.Component {
  return class AuthenticatedPage extends React.Component {
    static displayName = `withAuthenticationOnly(${Component.displayName
    || Component.name
    || 'AuthenticatedPage'})`

    static async getInitialProps({ctx}) {
      const {isServer, req, res, store} = ctx

      if (!req.session.token && res) {
        res.redirect(loginEndpoint)
      } else {
        store.dispatch(setToken(req.session.token))
      }

      let pageProps = {}
      if (Component.getInitialProps) {
        pageProps = await Component.getInitialProps(props)
      }

      return {isServer, ...pageProps}
    }

    render() {
      return <Component {...this.props} />
    }
  }
}
