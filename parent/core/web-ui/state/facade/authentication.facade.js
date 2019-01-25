import getStore from '../../getStore'

import {
  authenticationFailed as authenticationFailedAction,
  authenticationRequest,
  authenticationSucceed as authenticationSucceedAction
} from '../actions'

const dispatch = getStore().dispatch

export const requestAuthentication = () => {
  dispatch(authenticationRequest())
}

export const authenticationFailed = () => {
  dispatch(authenticationFailedAction())
}

export const authenticationSucceed = () => {
  dispatch(authenticationSucceedAction())
}
