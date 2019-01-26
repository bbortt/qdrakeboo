import App, {Container} from 'next/app'

import React from 'react'
import {Provider} from 'react-redux'
import withRedux from 'next-redux-wrapper'
import withReduxSaga from 'next-redux-saga'

import getStore from '../getStore'

import Footer from '../components/layout/footer.component'

require('./_app.scss')

class ReduxContextAwareApp extends App {

  static async getInitialProps({Component, ctx}) {
    let pageProps = {}

    if (Component.getInitialProps) {
      pageProps = await Component.getInitialProps({ctx})
    }

    return {pageProps}
  }

  render() {
    const {Component, pageProps, store} = this.props

    return (
      <Container>
        <Provider store={store}>
          <div className='container'>
            <Component {...pageProps} />
          </div>

          <Footer/>
        </Provider>
      </Container>
    )
  }
}

export default withRedux(getStore)(withReduxSaga({async: true})(ReduxContextAwareApp))
