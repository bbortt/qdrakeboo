// @flow
import React from 'react'
import { withRouter } from 'next/router'

import App, { Container } from 'next/app'

import { Provider } from 'react-redux'
import withRedux from 'next-redux-wrapper'
import withReduxSaga from 'next-redux-saga'
import configureStore from '../app/configureStore'

import Header from '../app/components/layout/Header'

import { completeUserInfo } from '../app/state/action'

import type { Page } from '../app/domain/Page.type'
import type { Context } from '../app/domain/Context.type'

require('./_app.scss')

export class ReduxContextAwareApp extends App {
  static async getInitialProps({
    Component,
    ctx,
  }: {
    Component: Page<any>,
    ctx: Context,
  }) {
    const { isServer, req, store, query } = ctx

    let pageProps = {}

    if (Component.getInitialProps) {
      pageProps = await Component.getInitialProps({ ctx })
    }

    store.dispatch(completeUserInfo(ctx))

    return { isServer, pageProps }
  }

  componentDidMount() {
    const foundation = require('foundation-sites')
    // $FlowFixMe
    $(document).foundation()
  }

  render() {
    const { Component, pageProps, store } = this.props

    const { isAuthenticated } = store.getState().userInfo

    return (
      <Container>
        <Provider store={store}>
          <Header isAuthenticated={isAuthenticated} />

          <Component {...pageProps} />
        </Provider>
      </Container>
    )
  }
}

export default withRedux(configureStore)(
  withReduxSaga(withRouter(ReduxContextAwareApp))
)
