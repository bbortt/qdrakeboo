// @flow
import React from 'react'

import Head from 'next/head'
import Router from 'next/router'

type IndexProps = {}

require('./index.scss')

class Index extends React.Component<IndexProps> {

  signIn() {
    Router.push('/login')
  }

  render() {
    return (
        <div className='index'>
          <Head>
            <title>My page title</title>
            <meta name="viewport" content="initial-scale=1.0, width=device-width" />
          </Head>

          <div className='container'>
            <h1>Welcome to Qdrakeboo</h1>
            <button type='button' className='button' onClick={this.signIn}>Sign
              In
            </button>
          </div>
        </div>
    )
  }
}

export default Index
