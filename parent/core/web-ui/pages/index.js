// @flow
import React from 'react'

import {connect} from 'react-redux'

import AnonymousPage from '../components/session/AnonymousPage';

class Index extends AnonymousPage {

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

export default connect()(Index)
