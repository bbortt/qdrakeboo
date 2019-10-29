// @flow
import React from 'react'

import Router from 'next/router'

import { Auth0Provider } from 'use-auth0-hooks'

import loadWellKnown from '../../util/loadWellKnown'

const onRedirectCallback = appState => {
  if (appState && appState.returnTo) {
    Router.push({
      pathname: appState.returnTo.pathname,
      query: appState.returnTo.query,
    })
  }
}

const onAccessTokenError = error => {
  console.error('Failed to retrieve access token: ', error)
}

const onLoginError = error => {
  Router.push({
    pathname: '/oops.html',
    query: {
      message: error.error_description || error.message,
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

class ContextfuldAuth0Provider extends React.Component {
  constructor(props) {
    super(props)

    this.state = { wellKnown: false }

    loadWellKnown().then(response =>
      this.setState({ wellKnown: response.data })
    )
  }

  render() {
    const { children } = this.props
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
        {children}
      </Auth0Provider>
    )
  }
}

export default ContextfuldAuth0Provider
