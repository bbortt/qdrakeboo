// @flow
import { BACKEND_UNREACHABLE } from '../action'
import type { HealthManagementAction } from '../action'

export type HealthManagementState = {
  online: boolean,
}

export const initialHealthManagementState: HealthManagementState = {
  online: true,
}

export default (
  state: HealthManagementState = initialHealthManagementState,
  action: HealthManagementAction
): HealthManagementState => {
  switch (action.type) {
    case BACKEND_UNREACHABLE:
      return {
        ...state,
        online: false,
      }
    default:
      return state
  }
}
