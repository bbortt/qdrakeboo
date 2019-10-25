// @flow
import uuidv1 from 'uuid/v1'

export type AlertType = 'ERROR' | 'WARN' | 'INFO' | 'SUCCESS'

const cssTypes = {
  ERROR: 'alert',
  WARN: 'warning',
  INFO: 'primary',
  SUCCESS: 'success',
}

export default class Alert {
  id: string

  type: string

  title: ?string

  message: string

  constructor(type: AlertType, title: ?string, message: string) {
    this.id = uuidv1()
    this.type = cssTypes[type]
    this.title = title
    this.message = message
  }
}
