import React from 'react'
import {connect} from 'react-redux'

import {requestAuthentication} from '../state/facade'

class Index extends React.Component {

  static async getInitialProps(props) {
    const {isServer} = props.ctx

    requestAuthentication(isServer)

    return {isServer}
  }

  render() {
    return (
      <a href='login'>
        <button>Sign In</button>
      </a>
    )
  }
}

export default connect()(Index)
