// @flow
import React from 'react'

import type {Account as AccountType} from '../app/domain/Account.type'

type AccountProps = {
  account: AccountType
}

class Account extends React.Component<AccountProps> {

  render() {
    const {account} = this.props;

    return (
        <div className='account'>
          <h1>Hi {account.displayName}</h1>
        </div>
    )
  }
}

export default Account
