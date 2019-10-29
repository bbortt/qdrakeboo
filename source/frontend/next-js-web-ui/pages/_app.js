// @flow
import React from 'react'

import App from 'next/app'
import Router from 'next/router'

import { Auth0Provider } from 'use-auth0-hooks'

import loadWellKnown from '../app/util/load-well-known'

import Foundation from '../app/components/layout/foundation'
import Header from '../app/components/layout/header/Header'

const onRedirectCallback = appState => {
  if (appState && appState.returnTo) {
    Router.push({
      pathname: appState.returnTo.pathname,
      query: appState.returnTo.query,
    })
  }
}

const onAccessTokenError = (err, options) => {
  console.error('Failed to retrieve access token: ', err)
}

const onLoginError = err => {
  Router.push({
    pathname: '/oops.html',
    query: {
      message: err.error_description || err.message,
    },
  })
}

const onRedirecting = () => {
  return (
    <div>
      <p>Signing you in..</p>
    </div>
  )
}

require('./_app.scss')

export class RootClass extends App {
  constructor(props) {
    super(props)

    this.state = { wellKnown: false }

    loadWellKnown().then(response =>
      this.setState({ wellKnown: response.data })
    )
  }

  render() {
    const { Component, pageProps } = this.props
    const { wellKnown } = this.state

    if (!wellKnown) {
      return <p>loading..</p>
    }

    return (
      <Auth0Provider
        domain={wellKnown.auth0.domain}
        clientId={wellKnown.auth0.clientId}
        redirectUri={wellKnown.auth0.callbackUrl}
        onLoginError={onLoginError}
        onAccessTokenError={onAccessTokenError}
        onRedirecting={onRedirecting}
        onRedirectCallback={onRedirectCallback}
      >
        <Foundation />
        <Header />

        <div className="root">
          <Component {...pageProps} />
        </div>
      </Auth0Provider>
    )
  }
}

export default RootClass
