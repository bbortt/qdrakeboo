// @flow
import React from 'react'

import App from 'next/app'

import axios from 'axios'

import Header from '../app/components/layout/Header'

import type {Page} from '../_next/Page.flow'
import type {Context} from '../_next/Context.flow'

require('./_app.scss')

class ReduxContextAwareApp extends App {

  static async getInitialProps({Component, ctx}: { Component: Page<any>, ctx: Context }) {
    const {req, query} = ctx

    const isServer = !!req

    let pageProps = {}

    if (Component.getInitialProps) {
      pageProps = await Component.getInitialProps({ctx})
    }

    if (isServer) {
      return {account: query.account, pageProps}
    } else {
      const res = await axios.get('/user-info')
      return {account: res.data, pageProps}
    }
  }

  componentDidMount() {
    const foundation = require('foundation-sites');
    $(document).foundation();
  }

  render() {
    const {account, Component, pageProps} = this.props

    const props = {
      ...pageProps,
      account
    }

    return (
        <div className='app'>
          <Header account={account}/>

          <Component {...props} />
        </div>
    )
  }
}

export default ReduxContextAwareApp
