// @flow
export type AlertType = 'ERROR' | 'WARN' | 'INFO' | 'SUCCESS'

const cssTypes = {
  ERROR: 'alert',
  WARN: 'warning',
  INFO: 'primary',
  SUCCESS: 'success',
}

export default class Alert {
  type: string

  title: ?string

  message: string

  constructor(type: AlertType, title: ?string, message: string) {
    this.type = cssTypes[type]
    this.title = title
    this.message = message
  }
}
