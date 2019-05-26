// @flow
import React from 'react'
import PropTypes from 'prop-types'

import Router from 'next/router'

import isAuthenticatedCall from '../app/common/security/isAuthenticated'

import type {InitialProps} from '../app/domain/next/InitialProps';

require('./index.scss')

type IndexPropTypes = {
  isAuthenticated: boolean
}

class Index extends React.Component<IndexPropTypes> {

  static async getInitialProps({ctx}: InitialProps) {
    const {isServer, req, res} = ctx

    const isAuthenticated = await isAuthenticatedCall({isServer, req, res})

    return {isAuthenticated}
  }

  launch = () => {
    Router.push('/session/renew')
  }

  render() {
    return (
        <div className='Index'>
          <div className='grid-container'>
            <div className='grid-x'>
              <div className='cell'>
                <h1>Welcome, stranger!</h1>
              </div>

              <div className='cell'>
                <button className='button'
                        onClick={this.launch}>{this.props.isAuthenticated
                    ? 'Launch' : 'Sign In'}</button>
              </div>
            </div>
          </div>
        </div>
    )
  }
}

export default Index
