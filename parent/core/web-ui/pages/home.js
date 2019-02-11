// @flow
import React from 'react'

import {connect} from 'react-redux'

import axios from 'axios'

import withAuthenticationOnly from '../lib/security/with-authentication-only';

class Home extends React.Component<Home.propTypes> {

  componentWillMount(): void {
    axios.get('http://localhost:8081/user')
    .then((response) => console.log('got: ', response))
    .catch((error) => console.log('sad.. ', error))
  }

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

export default connect()(withAuthenticationOnly(Home))
