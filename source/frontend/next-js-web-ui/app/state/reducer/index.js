// @flow
import { combineReducers } from 'redux'

import type { AlertState } from './alert.reducer'
import alertReducer, { initialAlertState } from './alert.reducer'

import type { SagaState } from './saga.reducer'
import sagaReducer, { initialSagaState } from './saga.reducer'

import type { UserManagementState } from './user-management.reducer'
import userManagementReducer, {
  initialUserManagementState,
} from './user-management.reducer'

export type ReduxState = {
  +alert: AlertState,
  +saga: SagaState,
  +userManagement: UserManagementState,
}

export const reduxState: ReduxState = {
  alert: initialAlertState,
  saga: initialSagaState,
  userManagement: initialUserManagementState,
}

export default combineReducers({
  alert: alertReducer,
  saga: sagaReducer,
  userManagement: userManagementReducer,
})
