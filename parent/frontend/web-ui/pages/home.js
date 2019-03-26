// @flow
import React from 'react'
import Router from 'next/router'

import {connect} from 'react-redux'

import {requestUserInfo} from '../app/state/actions'

class Home extends React.Component<Home.propTypes> {

  static async getInitialProps({ctx}) {
    const {isServer, req, res, store} = ctx

    store.dispatch(requestUserInfo({isServer, req, res}))

    return {}
  }

  logout = () => {
    Router.push('/logout')
  }

  render() {
    return (
      <div className='Home'>
        <h1>Hi there</h1>

        <p>
          User-Info: {JSON.stringify(this.props.userInfo)}
        </p>

        <button className='button' onClick={this.logout}>Logout</button>
      </div>
    )
  }
}

export default connect(state => state.session)(Home)
