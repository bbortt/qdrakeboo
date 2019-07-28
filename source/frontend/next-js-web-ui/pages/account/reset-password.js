// @flow
import React from 'react'

import AccountContainer from '../../app/components/account/AccountContainer'

type ResetPasswordProps = {}

export class ResetPassword extends React.Component<ResetPasswordProps> {
  render() {
    return (
        <AccountContainer>
          <div className='reset-password'>
            <h2>Reset Password</h2>
          </div>
        </AccountContainer>
    )
  }
}

export default ResetPassword
