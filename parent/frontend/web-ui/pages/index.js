// @flow
import React from 'react'
import Router from 'next/router'

import {connect} from 'react-redux'

class Index extends React.Component<Index.propTypes> {

  signIn = () => {
    Router.push('/session/renew')
  }

  render() {
    return (
      <div className='Index'>
        <h1>Welcome, stranger!</h1>

        <button className='button' onClick={this.signIn}>Sign In</button>
      </div>
    )
  }
}

export default connect(state => state.session)(Index)
