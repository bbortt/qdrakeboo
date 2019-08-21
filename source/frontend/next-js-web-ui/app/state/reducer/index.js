// @flow
import { combineReducers } from 'redux'

import type { AlertState } from './alert.reducer'
import alertReducer, { initialAlertState } from './alert.reducer'

import type { HealthManagementState } from './health-management.reducer'
import healthManagementReducer, {
  initialHealthManagementState,
} from './health-management.reducer'

import type { UserInfoState } from './user-info.reducer'
import userInfoReducer, { initialUserInfoState } from './user-info.reducer'

export type ReduxState = {
  +alert: AlertState,
  +health: HealthManagementState,
  +userInfo: UserInfoState,
}

export const reduxState: ReduxState = {
  alert: initialAlertState,
  health: initialHealthManagementState,
  userInfo: initialUserInfoState,
}

export default combineReducers({
  alert: alertReducer,
  health: healthManagementReducer,
  userInfo: userInfoReducer,
})
