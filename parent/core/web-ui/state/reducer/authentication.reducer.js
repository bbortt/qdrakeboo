import {AuthenticationActionTypes} from '../actions'

export const initialAuthenticationState = {
  auth: {},
  isAuthenticated: false
}

export default (state = initialAuthenticationState, action) => {
  switch (action.type) {
    case AuthenticationActionTypes.AUTHENTICATION_FAILED:
      return {
        ...state,
        isAuthenticated: false
      }
    case AuthenticationActionTypes.AUTHENTICATION_SUCCEED:
      return {
        ...state,
        auth: action.payload,
        isAuthenticated: true
      }
    default:
      return state
  }
}
