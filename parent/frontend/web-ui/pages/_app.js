// @flow
import React from 'react'

import App, {Container} from 'next/app'

import {Provider} from 'react-redux'
import withRedux from 'next-redux-wrapper'
import withReduxSaga from 'next-redux-saga'

import configureAxios from '../lib/axios/configureAxios'

import configureStore from '../configureStore'

class ReduxContextAwareApp extends App {

  static async getInitialProps({Component, ctx}) {
    const {isServer} = ctx

    configureAxios()

    let pageProps = {}

    if (Component.getInitialProps) {
      pageProps = await Component.getInitialProps({ctx})
    }

    return {isServer, ...pageProps}
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
