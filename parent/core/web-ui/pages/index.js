import React from 'react'
import {connect} from 'react-redux'

import {requestAuthentication} from '../state/facade'

class Index extends React.Component {

  static async getInitialProps(props) {
    const {isServer, store} = props.ctx

    requestAuthentication(isServer)

    return {isServer, isAuthenticated: store.getState().authenticationReducer.isAuthenticated}
  }

  render() {
    return (
      <div>
        <h1>Welcome, stranger!</h1>

        {
          this.props.isAuthenticated ?
            <a href='logout'>
              <button>Logout</button>
            </a>
            :
            <a href='login'>
              <button>Sign In</button>
            </a>
        }
      </div>
    )
  }
}

export default connect()(Index)
