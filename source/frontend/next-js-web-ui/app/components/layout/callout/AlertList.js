// @flow
import React from 'react'

import { connect } from 'react-redux'

import Alert from '../../../domain/alert.class'

import { Alert as AlertComponent } from './alert'

export type AlertListProps = {
  alerts: Alert[],
}

export class AlertList extends React.Component<AlertListProps> {
  render() {
    const { alerts } = this.props

    return (
      <div className="alert-list">
        {alerts.map((alert: Alert) => (
          <AlertComponent
            type={alert.type}
            title={alert.title}
            message={alert.message}
          />
        ))}
      </div>
    )
  }
}

export default connect(({ alert }) => {
  return { alerts: alert.alerts }
})(AlertList)
