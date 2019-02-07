import React from 'react';

import Router from 'next/router';

import {sessionRequest} from '../state/actions';

const loginEndpoint = '/login'

export default function withAuthenticationOnly(Component: React.Component): React.Component {
  return class AuthenticatedPage extends React.Component {
    static displayName = `withAuthenticationOnly(${Component.displayName
    || Component.name
    || 'AuthenticatedPage'})`

    static async getInitialProps({ctx}) {
      const {isServer, res, store} = ctx
      const session = store.getState().session

      if (!session.loading) {
        await store.dispatch(sessionRequest(isServer))
      }

      const unsubscribe = store.subscribe(() => {
        const updatedSession = store.getState().session

        if (!updatedSession.loading
            && Object.keys(updatedSession.oauth2).length === 0) {
          if (res) {
            res.redirect(loginEndpoint)
          } else {
            Router.push(loginEndpoint)
          }

          unsubscribe()
        }
      })

      let pageProps = {}
      if (Component.getInitialProps) {
        pageProps = await Component.getInitialProps(props)
      }

      return pageProps
    }

    render() {
      return <Component {...this.props} />
    }
  }
}
