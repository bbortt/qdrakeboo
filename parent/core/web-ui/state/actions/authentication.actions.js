export const AuthenticationActionTypes = {
  AUTHENTICATION_REQUEST: 'Authentication: Request',
  AUTHENTICATION_FAILED: 'Authentication: Failed',
  AUTHENTICATION_SUCCEED: 'Authentication: Succeed'
}

export const AuthenticationRequestAction = (isServer) => {
  return {
    type: AuthenticationActionTypes.AUTHENTICATION_REQUEST,
    payload: {
      'isServer': isServer
    }
  }
}

export const AuthenticationFailedAction = () => {
  return {
    type: AuthenticationActionTypes.AUTHENTICATION_FAILED
  }
}

export const AuthenticationSucceedAction = (auth) => {
  return {
    type: AuthenticationActionTypes.AUTHENTICATION_SUCCEED,
    payload: auth
  }
}
