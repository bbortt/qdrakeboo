// @flow
import React from 'react'

import {connect} from 'react-redux'

import {requestSession} from '../state/actions';

import withoutAuthenticationOnly from '../lib/security/without-authentication-only'

class Index extends React.Component<Index.propTypes> {

  signIn = () => {
    this.props.dispatch(requestSession())
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

export default connect()(withoutAuthenticationOnly(Index))
