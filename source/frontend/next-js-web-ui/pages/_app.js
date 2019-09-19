// @flow
import React from 'react'

import App from 'next/app'
import Head from 'next/head'

import { Provider } from 'react-redux'
import withRedux from 'next-redux-wrapper'
import withReduxSaga from 'next-redux-saga'
import configureStore from '../app/configureStore'

import requestPermissions from '../app/util/api/requestPermissions'

import { completeUserInfo, backendUnreachable } from '../app/state/action'

import type { Page } from '../app/domain/Page.type'
import type { Context } from '../app/domain/Context.type'

import BackendUnreachable from '../app/components/layout/error/BackendUnreachable'
import AlertList from '../app/components/layout/callout/AlertList'
import Header from '../app/components/layout/header/Header'

require('./_app.scss')

export class ReduxContextAwareAppClass extends App {
  static async getInitialProps({
    Component,
    ctx,
  }: {
    Component: Page<any>,
    ctx: Context,
  }) {
    const { isServer, query, store } = ctx

    const permissions = await requestPermissions(ctx)
    if (!Array.isArray(permissions)) {
      if (permissions === 500) {
        store.dispatch(backendUnreachable())
        return { isServer }
      }
    } else {
      store.dispatch(completeUserInfo(query.userInfo, permissions))
    }

    let pageProps = {}
    if (Component.getInitialProps) {
      pageProps = await Component.getInitialProps({ ctx })
    }
    pageProps = { ...pageProps }

    return { isServer, pageProps }
  }

  componentDidMount() {
    require('foundation-sites')
    // $FlowFixMe
    $(document).foundation()
  }

  render() {
    const { Component, pageProps, store } = this.props

    return (
      <div className="app">
        <Head>
          <title>Qdrakeboo</title>
          <meta
            name="viewport"
            content="initial-scale=1.0, width=device-width"
          />
        </Head>

        <Provider store={store}>
          <BackendUnreachable />
          <AlertList />
          <Header />

          <Component {...pageProps} />
        </Provider>
      </div>
    )
  }
}

export default withRedux(configureStore)(
  withReduxSaga(ReduxContextAwareAppClass)
)
