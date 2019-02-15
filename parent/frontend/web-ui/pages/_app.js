// @flow
import React from 'react'

import App, {Container} from 'next/app'

import {Provider} from 'react-redux'
import withRedux from 'next-redux-wrapper'
import withReduxSaga from 'next-redux-saga'

import configureStore from '../configureStore'

import authenticationErrorHandler
  from '../lib/security/authentication-error-handler.interceptor'

class ReduxContextAwareApp extends App {

  static async getInitialProps({Component, ctx}) {
    const {isServer} = ctx
    let pageProps = {}

    if (Component.getInitialProps) {
      pageProps = await Component.getInitialProps({ctx})
    }

    return {isServer, ...pageProps}
  }

  constructor(props) {
    super(props)

    const {store} = this.props

    authenticationErrorHandler(store)
  }

  render() {
    const {Component, pageProps, store} = this.props

    return (
        <Container>
          <Provider store={store}>
            <Component {...pageProps} />
          </Provider>
        </Container>
    )
  }
}

export default withRedux(configureStore)(withReduxSaga(ReduxContextAwareApp))
