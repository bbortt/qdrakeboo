import React from 'react'
import {connect} from 'react-redux'

import {requestAuthentication} from '../state/facade/authentication.facade'

class Index extends React.Component {

  constructor(props) {
    super(props)

    this.state = {
      isAuthenticated: props.isAuthenticated
    }
  }

  static async getInitialProps(props) {
    const {store, isServer} = props.ctx

    requestAuthentication(props.ctx)

    return {isServer}
  }

  render() {
    const {isAuthenticated} = this.state

    return (
        <div>
          <h1>Welcome, stranger!</h1>

          {
            isAuthenticated ?
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

const mapStateToProps = (state) => {
  return {
    isAuthenticated: state.authentication.isAuthenticated
  }
}

export default connect(mapStateToProps)(Index)
