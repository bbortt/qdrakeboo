import getStore from '../../getStore'

import {AuthenticationRequestAction} from '../actions'

const dispatch = getStore().dispatch

export const requestAuthentication = (isServer) => {
  dispatch(AuthenticationRequestAction(isServer))
}
