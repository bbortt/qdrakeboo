// @flow
import React from 'react'
import PropTypes from 'prop-types'

import Router from 'next/router'

import {requestUserInfo} from '../app/common/security/sessionUtils'

import type {UserInfo} from '../app/domain/session/UserInfo';

require('./index.scss')

class Index extends React.Component<Index.propTypes> {

  constructor(props) {
    super(props)

    console.log('props: ', props)
  }

  static async getInitialProps({ctx}) {
    const {isServer, req, res, store} = ctx

    const response = await requestUserInfo({isServer, req, res})

    return {userInfo: response}
  }

  launch = () => {
    Router.push('/home')
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
                        onClick={this.launch}>{this.props.userInfo ? 'Launch'
                    : 'Sign In'}</button>
              </div>
            </div>
          </div>
        </div>
    )
  }
}

export default Index
