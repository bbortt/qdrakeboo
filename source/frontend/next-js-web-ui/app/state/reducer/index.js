// @flow
import { combineReducers } from 'redux'

import alertReducer, { initialAlertState } from './alert.reducer'
import type { AlertState } from './alert.reducer'

import userInfoReducer, { initialUserInfoState } from './user-info.reducer'
import type { UserInfoState } from './user-info.reducer'

export type ReduxState = {
  +alert: AlertState,
  +userInfo: UserInfoState,
}

export const reduxState: ReduxState = {
  alert: initialAlertState,
  userInfo: initialUserInfoState,
}

export default combineReducers({
  alert: alertReducer,
  userInfo: userInfoReducer,
})
