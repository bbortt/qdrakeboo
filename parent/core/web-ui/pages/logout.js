import React from 'react'
import {connect} from 'react-redux'

import {AuthenticationRequestAction} from '../state/actions'

class Index extends React.Component {

  static async getInitialProps(props) {
    const {isServer, store} = props.ctx

    store.dispatch(AuthenticationRequestAction(isServer))

    return {isServer}
  }

  render() {
    return (
        <div>
          <h1>There he goes - the poor lonesome cowboy..</h1>

          <a href='/'>
            <button>Return</button>
          </a>
        </div>
    )
  }
}

export default connect()(Index)
