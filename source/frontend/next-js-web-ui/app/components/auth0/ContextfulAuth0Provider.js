// @flow
import React from 'react'
import type { Node } from 'react'

import Router, { withRouter } from 'next/router'

import { Auth0Provider } from 'use-auth0-hooks'

import getStore from '../../getStore'
import { addErrorAlert } from '../../state/action'

import type { WellKnownType } from '../../domain/WellKnown.type'

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
  getStore().dispatch(addErrorAlert(error /* TODO: i18n code title */))
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

type ContextfuldAuth0ProviderProps = {
  children: Node,
  pathname: string,
}

class ContextfuldAuth0Provider extends React.Component<
  ContextfuldAuth0ProviderProps,
  { wellKnown: WellKnownType }
> {
  constructor(props: ContextfuldAuth0ProviderProps) {
    super(props)

    this.state = { wellKnown: {} }

    loadWellKnown().then(({ wellKnown }) => this.setState({ wellKnown }))
  }

  render() {
    const { children, pathname } = this.props
    const { wellKnown } = this.state

    if (Object.keys(wellKnown).length === 0) {
      if (pathname === '/') {
        return children
      }

      return null
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

export default withRouter(ContextfuldAuth0Provider)
