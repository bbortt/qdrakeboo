// @flow
import React from 'react'

import App from 'next/app'
import Head from 'next/head'

import { Provider } from 'react-redux'
import getStore from '../app/getStore'

import updateFoundation from '../app/util/updateFoundation'

import ContextfuldAuth0Provider from '../app/components/auth0/ContextfulAuth0Provider'
import Header from '../app/components/layout/header/Header'

require('./_app.scss')

export class RootClass extends App {
  componentDidMount() {
    updateFoundation()
  }

  render() {
    const { Component, pageProps } = this.props

    return (
      <ContextfuldAuth0Provider>
        <Head>
          <title>Qdrakeboo</title>
        </Head>

        <Provider store={getStore()}>
          <Header />

          <div id="root">
            <Component {...pageProps} />
          </div>
        </Provider>
      </ContextfuldAuth0Provider>
    )
  }
}

export default RootClass
