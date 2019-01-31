// @flow
import React from 'react'

import {connect} from 'react-redux'

import AuthenticatedPage from '../components/session/AuthenticatedPage';

class Home extends AuthenticatedPage {

  render() {
    return (
        <div className='Home'>
          <h1>Hi there</h1>

          <p>Your current auth looks like the following:
          </p>

          <a href='logout'>
            <button>Logout</button>
          </a>
        </div>
    )
  }
}

export default connect()(Home)
