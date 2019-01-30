import {AuthenticationRequestAction} from '../actions'

export const requestAuthentication = (context) => {
  context.store.dispatch(AuthenticationRequestAction(context.isServer))
}
