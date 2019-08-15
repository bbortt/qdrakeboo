// @flow
import { combineReducers } from 'redux'

import userInfoReducer, { initialUserInfoState } from './user-info.reducer'
import type { UserInfoState } from './user-info.reducer'

export type ReduxState = {
  +userInfo: UserInfoState,
}

export const initialState: ReduxState = {
  userInfo: initialUserInfoState,
}

export default combineReducers({
  userInfo: userInfoReducer,
})
