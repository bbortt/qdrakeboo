// @flow
import React from 'react'

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
