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
    const {isServer} = ctx

    let pageProps = {}

    if (Component.getInitialProps) {
      pageProps = await Component.getInitialProps({ctx})
    }

    return {isServer, pageProps}
  }

  render() {
    const {Component, pageProps} = this.props

    return (
        <div className='app'>
          <Header/>

          <Component {...pageProps} />
        </div>
    )
  }
}

export default ReduxContextAwareApp
