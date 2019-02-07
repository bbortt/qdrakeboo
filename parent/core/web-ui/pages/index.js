// @flow
import React from 'react'

import {connect} from 'react-redux'

import withoutAuthenticationOnly from '../lib/without-authentication-only';

class Index extends React.Component<Index.propTypes> {

  render() {
    return (
        <div className='Index'>
          <h1>Welcome, stranger!</h1>

          <a href='login'>
            <button>Sign In</button>
          </a>
        </div>
    )
  }
}

export default connect()(withoutAuthenticationOnly(Index))
