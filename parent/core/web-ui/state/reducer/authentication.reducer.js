import {AuthenticationTypes} from '../actions'

const initialState = {
  auth: {},
  isAuthenticated: false
}

const authenticationReducer = (state = initialState, action) => {
  switch (action.type) {
    case AuthenticationTypes.AUTHENTICATION_FAILED:
      return {
        ...state,
        isAuthenticated: false
      }
    case AuthenticationTypes.AUTHENTICATION_SUCCEED:
      return {
        ...state,
        auth: action.payload,
        isAuthenticated: true
      }
    default:
      return state
  }
}

export default authenticationReducer
