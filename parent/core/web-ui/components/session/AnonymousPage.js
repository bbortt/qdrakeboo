// @flow
import React from 'react'

import Router from 'next/router'

import {requestSession} from '../../state/facade';

const authenticatedEntryPoint = '/home'

class AnonymousPage extends React.Component<AnonymousPage.propTypes> {

  static async getInitialProps({ctx}: any) {
    const {isServer, res, store} = ctx

    requestSession(ctx)

    const unsubscribe = store.subscribe(() => {
      const {session} = store.getState();

      if (session.requested && session.isAuthenticated) {
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
