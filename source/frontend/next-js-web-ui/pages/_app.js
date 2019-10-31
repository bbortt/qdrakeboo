// @flow
import React from 'react'

import App from 'next/app'

import { Provider } from 'react-redux'
import configureStore from '../app/configureStore'

import updateFoundation from '../app/util/updateFoundation'

import ContextfuldAuth0Provider from '../app/components/auth0/ContextfulAuth0Provider'
import Header from '../app/components/layout/header/Header'

require('./_app.scss')

const store = configureStore()

export class RootClass extends App {
  componentDidMount() {
    updateFoundation()
  }

  render() {
    const { Component, pageProps } = this.props

    return (
      <ContextfuldAuth0Provider>
        <Provider store={store}>
          <Header />

          <div className="root">
            <Component {...pageProps} />
          </div>
        </Provider>
      </ContextfuldAuth0Provider>
    )
  }
}

export default RootClass
