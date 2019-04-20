// @flow
import React from 'react'
import Router from 'next/router'

import {connect} from 'react-redux'
import {requestUserInfo} from '../app/state/actions';

require('./index.scss')

class Index extends React.Component<Index.propTypes> {

  static async getInitialProps({ctx}) {
    const {isServer, req, res, store} = ctx

    store.dispatch(requestUserInfo({isServer, req, res}))

    return {}
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
              <button className='button' onClick={this.launch}>{this.props.userInfo ? 'Launch' : 'Sign In'}</button>
            </div>
          </div>
        </div>
      </div>
    )
  }
}

export default connect(state => state.session)(Index)
