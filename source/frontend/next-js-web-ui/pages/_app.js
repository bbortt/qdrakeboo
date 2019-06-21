// @flow
import React from 'react'

import App from 'next/app'
import dynamic from 'next/dynamic'

import Header from '../app/components/layout/Header';

// Styles
require('./_app.scss')
dynamic(import('jquery/dist/jquery.min'), {ssr: false})
dynamic(import('what-input/dist/what-input.min'), {ssr: false})
dynamic(import('foundation-sites/dist/js/foundation.min'), {ssr: false})

class ReduxContextAwareApp extends App {

  static async getInitialProps({Component, ctx}) {
    const {isServer, query} = ctx

    let pageProps = {}

    if (Component.getInitialProps) {
      pageProps = await Component.getInitialProps({ctx})
    }

    return {account: query.account, isServer, pageProps}
  }

  render() {
    const {account, Component, pageProps} = this.props;

    const props = {
      ...pageProps,
      account
    };

    return (
        <div className='app'>
          <Header/>

          <Component {...props} />
        </div>
    )
  }
}

export default ReduxContextAwareApp
