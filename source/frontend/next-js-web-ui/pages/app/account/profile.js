// @flow
import React from 'react'

import { withAuth } from 'use-auth0-hooks'

import AccountContainer from '../../../app/components/account/AccountContainer'

export const ProfileClass = ({ auth }) => {
  const { user } = auth
  const { name } = user

  return (
    <AccountContainer>
      <div className="profile">
        <h2>Hi {name}</h2>
      </div>
    </AccountContainer>
  )
}

export default withAuth(ProfileClass)
