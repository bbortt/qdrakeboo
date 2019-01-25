export const AuthenticationTypes = {
  AUTHENTICATION_REQUEST: 'Authentication: Request',
  AUTHENTICATION_FAILED: 'Authentication: Failed',
  AUTHENTICATION_SUCCEED: 'Authentication: Succeed'
}

export const authenticationRequest = () => {
  return {
    type: AuthenticationTypes.AUTHENTICATION_REQUEST
  }
}

export const authenticationFailed = () => {
  return {
    type: AuthenticationTypes.AUTHENTICATION_FAILED
  }
}

export const authenticationSucceed = () => {
  return {
    type: AuthenticationTypes.AUTHENTICATION_SUCCEED
  }
}
