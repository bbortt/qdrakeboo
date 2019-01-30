import React from 'react'
import {Provider} from 'react-redux'

import App, {Container} from 'next/app'

import withRedux from 'next-redux-wrapper'
import withReduxSaga from 'next-redux-saga'

import {requestAuthentication} from '../state/facade/authentication.facade';

import configureStore from '../configureStore'

class ReduxContextAwareApp extends App {

  static getInitialProps({Component, ctx}) {
    let pageProps = {}

    requestAuthentication(ctx)

    if (Component.getInitialProps) {
      pageProps = Component.getInitialProps({ctx})
    }

    return {pageProps}
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

export default withRedux(configureStore)(
    withReduxSaga()(ReduxContextAwareApp))
