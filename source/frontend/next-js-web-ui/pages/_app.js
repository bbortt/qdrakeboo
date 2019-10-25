import React from 'react';

import App from 'next/app';
import config from 'next/config'
import Router from 'next/router';

import {Auth0Provider} from 'use-auth0-hooks';

import fetch from 'isomorphic-unfetch'

const onRedirectCallback = appState => {
  if (appState && appState.returnTo) {
    Router.push({
      pathname: appState.returnTo.pathname,
      query: appState.returnTo.query
    })
  }
};

const onAccessTokenError = (err, options) => {
  console.error('Failed to retrieve access token: ', err);
};

const onLoginError = (err) => {
  Router.push({
    pathname: '/oops.html',
    query: {
      message: err.error_description || err.message
    }
  })
};

const onRedirecting = () => {
  return (
      <div>
        <h1>Signing you in</h1>
        <p>
          In order to access this page you will need to sign in.
          Please wait while we redirect you to the login page...
        </p>
      </div>
  );
};

export class RootClass extends App {
  render() {
    const {Component, pageProps} = this.props;
    const {publicRuntimeConfig} = config();

    return (
        <Auth0Provider
            domain={publicRuntimeConfig.auth0.domain}
            clientId={publicRuntimeConfig.auth0.clientId}
            redirectUri={publicRuntimeConfig.auth0.callbackUrl}
            onLoginError={onLoginError}
            onAccessTokenError={onAccessTokenError}
            onRedirecting={onRedirecting}
            onRedirectCallback={onRedirectCallback}>
          <Component {...pageProps} />
        </Auth0Provider>
    );
  }
}

export default RootClass
