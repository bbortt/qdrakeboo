// @flow
import Alert from '../../domain/alert.class'

export const ADD_ALERT: string = 'Alert: Add'

export type AddAlertAction = {
  type: string,
  alert: Alert,
}

export type AlertAction = AddAlertAction

export const addErrorAlert = (
  message: string,
  title: ?string
): AddAlertAction => {
  return { type: ADD_ALERT, alert: new Alert('ALERT', title, message) }
}
