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
      <div>
        <h1>Logged out!</h1>

        <a href='/'>
          <button>Back to index</button>
        </a>
      </div>
    )
  }
}

export default connect()(Index)
