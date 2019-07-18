// @flow
import React from 'react'

import type { Account as AccountType } from '../../app/domain/Account.type'

type SettingsProps = {
  account: AccountType,
}

class Settings extends React.Component<SettingsProps> {
  render() {
    const { account } = this.props

    return (
      <div className="settings">
        <h1>Hi {account.displayName}</h1>
      </div>
    )
  }
}

export default Settings
