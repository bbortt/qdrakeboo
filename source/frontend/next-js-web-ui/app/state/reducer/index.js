// @flow
import { combineReducers } from 'redux'

import type { AlertState } from './alert.reducer'
import alertReducer, { initialAlertState } from './alert.reducer'

import type { UserManagementState } from './user-management.reducer'
import userManagementReducer, {
  initialUserManagementState,
} from './user-management.reducer'

export type ReduxState = {
  +alert: AlertState,
  +userManagement: UserManagementState,
}

export const reduxState: ReduxState = {
  alert: initialAlertState,
  userManagement: initialUserManagementState,
}

export default combineReducers({
  alert: alertReducer,
  userManagement: userManagementReducer,
})
