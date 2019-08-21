// @flow
import React from 'react'
import { connect } from 'react-redux'

require('./backend-unreachable.scss')

type BackendUnreachableProps = {
  +online: boolean,
}

export class BackendUnreachableClass extends React.Component<BackendUnreachableProps> {
  render() {
    const { online } = this.props

    if (online) {
      return null
    }

    return (
      <div className="backend-unreachable">
        <div className="container">
          <h4>We&#39;re having trouble reaching our servers..</h4>
          <p>Please check your internet connection and try again.</p>
        </div>
      </div>
    )
  }
}

export default connect(({ health }) => {
  return { online: health.online }
})(BackendUnreachableClass)
