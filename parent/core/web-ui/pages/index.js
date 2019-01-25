import React from 'react'
import {connect} from 'react-redux'

import getConfig from 'next/config'

import {requestAuthentication} from '../state/facade'

const {publicRuntimeConfig} = getConfig()

class Index extends React.Component {

  static async getInitialProps(props) {
    const {isServer} = props.ctx

    requestAuthentication()

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
