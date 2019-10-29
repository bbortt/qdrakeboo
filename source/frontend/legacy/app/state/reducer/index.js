// @flow
import { combineReducers } from 'redux'

import type { AlertState } from './alert.reducer'
import alertReducer, { initialAlertState } from './alert.reducer'

import type { HealthManagementState } from './health-management.reducer'
import healthManagementReducer, {
  initialHealthManagementState,
} from './health-management.reducer'

import type { SagaState } from './saga.reducer'
import sagaReducer, { initialSagaState } from './saga.reducer'

import type { UserInfoState } from './user-info.reducer'
import userInfoReducer, { initialUserInfoState } from './user-info.reducer'

import type { UserManagementState } from './user-management.reducer'
import userManagementReducer, {
  initialUserManagementState,
} from './user-management.reducer'

export type ReduxState = {
  +alert: AlertState,
  +health: HealthManagementState,
  +saga: SagaState,
  +userInfo: UserInfoState,
  +userManagement: UserManagementState,
}

export const reduxState: ReduxState = {
  alert: initialAlertState,
  health: initialHealthManagementState,
  saga: initialSagaState,
  userInfo: initialUserInfoState,
  userManagement: initialUserManagementState,
}

export default combineReducers({
  alert: alertReducer,
  health: healthManagementReducer,
  saga: sagaReducer,
  userInfo: userInfoReducer,
  userManagement: userManagementReducer,
})