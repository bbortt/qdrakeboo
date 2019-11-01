// @flow
import React from 'react'

import { withAuth } from 'use-auth0-hooks'

import type { AuthType } from '../../../app/domain/Auth.type'

import AccountContainer from '../../../app/components/account/AccountContainer'

export const ProfileClass = ({ auth }: { auth: AuthType }) => {
  const { isAuthenticated, user } = auth

  if (!isAuthenticated) {
    return null
  }

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
