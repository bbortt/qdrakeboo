import getStore from '../../getStore'

import {AuthenticationFailedAction, AuthenticationRequestAction, AuthenticationSucceedAction} from '../actions'

const dispatch = getStore().dispatch

export const requestAuthentication = (isServer) => {
  dispatch(AuthenticationRequestAction(isServer))
}

export const authenticationFailed = () => {
  dispatch(AuthenticationFailedAction())
}

export const authenticationSucceed = (auth) => {
  dispatch(AuthenticationSucceedAction(auth))
}
