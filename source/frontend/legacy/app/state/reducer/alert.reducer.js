// @flow
import { ADD_ALERT, CLOSE_ALERT } from '../action'
import type { AddAlertAction, AlertAction, CloseAlertAction } from '../action'

import Alert from '../../domain/alert.class'

export type AlertState = {
  alerts: Alert[],
}

export const initialAlertState: AlertState = {
  alerts: [],
}

export default (
  state: AlertState = initialAlertState,
  action: AlertAction
): AlertState => {
  switch (action.type) {
    case ADD_ALERT:
      const addAlertAction = ((action: any): AddAlertAction)
      const { alert } = addAlertAction

      state.alerts.push(alert)

      return {
        ...state,
      }
    case CLOSE_ALERT:
      const closeAlertAction = ((action: any): CloseAlertAction)
      const { id } = closeAlertAction

      const newAlerts = state.alerts.filter(
        (existingAlert: Alert) => existingAlert.id !== id
      )

      return {
        ...state,
        alerts: newAlerts,
      }
    default:
      return state
  }
}
