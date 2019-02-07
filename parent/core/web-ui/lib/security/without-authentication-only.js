import React from 'react';

import Router from 'next/router';

import {sessionRequest} from '../../state/actions';

const authenticatedEntryPoint = '/home'

export default function withoutAuthenticationOnly(Component: React.Component): React.Component {
  return class AnonymousPage extends React.Component {
    static displayName = `withoutAuthenticationOnly(${Component.displayName
    || Component.name
    || 'AnonymousPage'})`

    static async getInitialProps({ctx}) {
      const {isServer, res, store} = ctx
      const session = store.getState().session;

      if (!session.loading) {
        await store.dispatch(sessionRequest(isServer))
      }

      const unsubscribe = store.subscribe(() => {
        const updatedSession = store.getState().session

        console.log('updated: ', updatedSession)

        if (!updatedSession.loading
            && Object.keys(updatedSession.oauth2).length !== 0) {
          if (res) {
            res.redirect(authenticatedEntryPoint)
          } else {
            Router.push(authenticatedEntryPoint)
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
