import React from 'react'

import Router from 'next/router'

import {requestAuthentication} from '../../state/facade/authentication.facade';

const loginEndpoint = '/login'

class AuthenticatedPage extends React.Component {

  static async getInitialProps({ctx}) {
    const {isServer, res, store} = ctx

    requestAuthentication(ctx)

    const unsubscribe = store.subscribe(() => {
      if (!store.getState().authentication.isAuthenticated) {
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
