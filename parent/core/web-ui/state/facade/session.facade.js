// @flow
import {Store} from 'redux'
import {sessionRequest} from '../actions'

export const requestSession = (context: { store: Store, isServer: boolean }) => {
  context.store.dispatch(sessionRequest(context.isServer))
}
