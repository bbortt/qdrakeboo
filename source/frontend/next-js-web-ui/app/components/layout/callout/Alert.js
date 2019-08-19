// @flow
import React from 'react'
import type { AlertType } from '../../../domain/alert.class'

export type AlertProps = {
  id: string,
  type: AlertType,
  title: ?string,
  message: string,
}

export class Alert extends React.Component<AlertProps> {
  render() {
    const { id, type, title, message } = this.props

    return (
      <div className={`alert callout ${type}`}>
        <h5>{title || type}</h5>
        <p>{message}</p>
      </div>
    )
  }
}

export default Alert
