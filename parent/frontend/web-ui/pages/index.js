// @flow
import React from 'react'
import Router from 'next/router'

import {connect} from 'react-redux'

class Index extends React.Component<Index.propTypes> {

  signIn = () => {
    Router.push('/session')
  }

  render() {
    return (
      <div className='Index'>
        <h1>Welcome, stranger!</h1>

        <button onClick={this.signIn}>Sign In</button>
      </div>
    )
  }
}

export default connect()(Index)
