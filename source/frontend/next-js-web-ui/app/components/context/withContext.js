// @flow
import React from 'react'

import { ReactReduxContext } from 'react-redux'

export default Component => {
  return props => {
    return (
      <ReactReduxContext.Consumer>
        {({ store }) => {
          return <Component {...props} store={store} />
        }}
      </ReactReduxContext.Consumer>
    )
  }
}
