export const AuthenticationTypes = {
  AUTHENTICATION_REQUEST: 'Authentication: Request',
  AUTHENTICATION_FAILED: 'Authentication: Failed',
  AUTHENTICATION_SUCCEED: 'Authentication: Succeed'
}

export const AuthenticationRequestAction = (isServer) => {
  return {
    type: AuthenticationTypes.AUTHENTICATION_REQUEST,
    payload: {
      'isServer': isServer
    }
  }
}

export const AuthenticationFailedAction = () => {
  return {
    type: AuthenticationTypes.AUTHENTICATION_FAILED
  }
}

export const AuthenticationSucceedAction = (auth) => {
  return {
    type: AuthenticationTypes.AUTHENTICATION_SUCCEED,
    payload: auth
  }
}
