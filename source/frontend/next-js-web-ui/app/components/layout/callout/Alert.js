// @flow
import React from 'react'

import withContext from '../../context/withContext'
import { closeAlert } from '../../../state/action'

import Alert from '../../../domain/alert.class'

export type AlertProps = {
  alert: Alert,
  store: {
    dispatch: (action: any) => void,
  },
}

export class AlertClass extends React.Component<AlertProps> {
  constructor(props: AlertProps) {
    super(props)

    this.handleClose = this.handleClose.bind(this)
  }

  handleClose = (event: SyntheticInputEvent<HTMLButtonElement>) => {
    event.preventDefault()

    const { alert, store } = this.props

    store.dispatch(closeAlert(alert.id))
  }

  render() {
    const { alert } = this.props
    const { id, type, title, message } = alert

    return (
      <div className={`alert callout ${type}`} id={`alert-${id}`}>
        <h5>{title || type}</h5>
        <p>{message}</p>
        <button
          className="close-button"
          aria-label="Dismiss alert"
          type="button"
          onClick={this.handleClose}
        >
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
    )
  }
}

export default withContext(AlertClass)
