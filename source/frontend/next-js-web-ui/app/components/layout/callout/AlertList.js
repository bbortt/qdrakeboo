// @flow
import React from 'react'

import { connect } from 'react-redux'

import Alert from '../../../domain/alert.class'

import AlertComponent from './alert'

export type AlertListProps = {
  alerts: Alert[],
}

require('./alert-list.scss')

export class AlertListClass extends React.Component<AlertListProps> {
  render() {
    const { alerts } = this.props

    return (
      <div className="alert-list">
        {alerts.map((alert: Alert, index: number) => (
          <React.Fragment key={`alert-list-item-${index}`}>
            <AlertComponent alert={alert} />
          </React.Fragment>
        ))}
      </div>
    )
  }
}

export default connect(({ alert }) => {
  return { alerts: alert.alerts }
})(AlertListClass)
