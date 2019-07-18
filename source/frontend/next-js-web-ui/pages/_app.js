// @flow
import React from 'react'
import { withRouter } from 'next/router'

import App, { Container } from 'next/app'

import { Provider } from 'react-redux'
import withRedux from 'next-redux-wrapper'
import withReduxSaga from 'next-redux-saga'
import configureStore from '../app/configureStore'

import axios from 'axios'

import Header from '../app/components/layout/Header'

import type { Page } from '../_next/Page.flow'
import type { Context } from '../_next/Context.flow'

require('./_app.scss')

class ReduxContextAwareApp extends App {
  static async getInitialProps({
    Component,
    ctx,
  }: {
    Component: Page<any>,
    ctx: Context,
  }) {
    const { req, query } = ctx

    const isServer = !!req

    let pageProps = {}

    if (Component.getInitialProps) {
      pageProps = await Component.getInitialProps({ ctx })
    }

    if (isServer) {
      return { account: query.account, isServer, pageProps }
    } else {
      const res = await axios.get('/user-info')
      return { account: res.data, isServer, pageProps }
    }
  }

  componentDidMount() {
    const foundation = require('foundation-sites')
    // $FlowFixMe
    $(document).foundation()
  }

  render() {
    const { account, Component, pageProps, store } = this.props

    const props = {
      ...pageProps,
      account,
    }

    return (
      <Container>
        <Provider store={store}>
          <Header account={account} />

          <Component {...props} />
        </Provider>
      </Container>
    )
  }
}

export default withRedux(configureStore)(
  withReduxSaga(withRouter(ReduxContextAwareApp))
)
