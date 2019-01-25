import {AuthenticationTypes} from '../actions'

const initialState = {
  isAuthenticated: false
}

const authenticationReducer = (state = initialState, action) => {
  switch (action.type) {
    case AuthenticationTypes.AUTHENTICATION_FAILED:
      return {
        isAuthenticated: false,
        ...state
      }
    case AuthenticationTypes.AUTHENTICATION_SUCCEED:
      return {
        isAuthenticated: true,
        ...state
      }
    default:
      return state
  }
}

export default authenticationReducer
