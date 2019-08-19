// @flow
import type { AddAlertAction, AlertAction } from '../action'
import { ADD_ALERT } from '../action'
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

      return state
    default:
      return state
  }
}
