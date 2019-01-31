import React from 'react'

import Router from 'next/router'

import {requestAuthentication} from '../../state/facade/authentication.facade';

const authenticatedEntryPoint = '/home'

class AnonymousPage extends React.Component {

  static async getInitialProps({ctx}) {
    const {isServer, res, store} = ctx

    requestAuthentication(ctx)

    const unsubscribe = store.subscribe(() => {
      if (store.getState().authentication.isAuthenticated) {
        if (res) {
          res.redirect(authenticatedEntryPoint)
        } else {
          Router.push(authenticatedEntryPoint)
        }

        unsubscribe()
      }
    })

    return {isServer}
  }
}

export default AnonymousPage
