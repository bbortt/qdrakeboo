// @flow
import * as React from 'react'

import { ReactReduxContext } from 'react-redux'

export default (Component: React.ComponentType<any>) => {
  return (props: any) => {
    return (
      <ReactReduxContext.Consumer>
        {({ store }) => {
          return <Component {...props} store={store} />
        }}
      </ReactReduxContext.Consumer>
    )
  }
}
