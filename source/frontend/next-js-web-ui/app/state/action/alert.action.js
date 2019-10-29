// @flow
import Alert from '../../domain/alert.class'

export const ADD_ALERT: string = 'Alert: Add'
export const CLOSE_ALERT: string = 'Alert: Close'

export type AddAlertAction = {
  type: string,
  alert: Alert,
}

export type CloseAlertAction = {
  type: string,
  id: string,
}

export type AlertAction = AddAlertAction | CloseAlertAction

export const addErrorAlert = (
  message: string,
  title: ?string
): AddAlertAction => {
  return { type: ADD_ALERT, alert: new Alert('ERROR', title, message) }
}

export const addSuccessAlert = (
  message: string,
  title: ?string
): AddAlertAction => {
  return { type: ADD_ALERT, alert: new Alert('SUCCESS', title, message) }
}

export const closeAlert = (id: string): CloseAlertAction => {
  return { type: CLOSE_ALERT, id }
}
