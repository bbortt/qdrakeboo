import {combineReducers} from 'redux'

import authenticationReducer, {initialAuthenticationState} from './authentication.reducer'

export const initialState = {
  authentication: initialAuthenticationState
}

export default combineReducers({
  authentication: authenticationReducer
})
