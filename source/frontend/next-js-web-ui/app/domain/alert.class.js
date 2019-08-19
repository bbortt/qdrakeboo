// @flow
export type AlertType = 'ERROR' | 'WARN' | 'INFO'

export default class Alert {
  type: AlertType

  title: ?string

  message: string

  constructor(type: AlertType, title: ?string, message: string) {
    this.type = type
    this.title = title
    this.message = message
  }
}
