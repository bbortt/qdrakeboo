// @flow
import React from 'react'

import Router from 'next/router'

import {requestSession} from '../../state/facade';

const loginEndpoint = '/login'

class AuthenticatedPage extends React.Component<AuthenticatedPage.propTypes> {

  static async getInitialProps({ctx}: any) {
    const {isServer, res, store} = ctx

    requestSession(ctx)

    const unsubscribe = store.subscribe(() => {
      const {session} = store.getState();

      if (session.requested && !session.isAuthenticated) {
        if (res) {
          res.redirect(loginEndpoint)
        } else {
          Router.push(loginEndpoint)
        }

        unsubscribe()
      }
    })

    return {isServer}
  }
}

export default AuthenticatedPage
