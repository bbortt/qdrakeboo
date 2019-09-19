// @flow
import type { ResetPasswordFailedAction, UserManagementAction } from '../action'
import {
  RESET_PASSWORD,
  RESET_PASSWORD_FAILED,
  RESET_PASSWORD_SUCCEED,
} from '../action'

export type UserManagementState = {
  +resetPassword: {
    loading: boolean,
    error: string,
  },
}

export const initialUserManagementState: UserManagementState = {
  resetPassword: {
    loading: false,
    error: '',
  },
}

export default (
  state: UserManagementState = initialUserManagementState,
  action: UserManagementAction
): UserManagementState => {
  switch (action.type) {
    case RESET_PASSWORD:
      return {
        ...state,
        resetPassword: {
          loading: true,
          error: state.resetPassword.error,
        },
      }
    case RESET_PASSWORD_SUCCEED:
      return {
        ...state,
        resetPassword: {
          loading: false,
          error: '',
        },
      }
    case RESET_PASSWORD_FAILED:
      const resetPasswordFailedAction = ((action: any): ResetPasswordFailedAction)
      const { error } = resetPasswordFailedAction

      return {
        ...state,
        resetPassword: {
          loading: false,
          error,
        },
      }
    default:
      return state
  }
}
