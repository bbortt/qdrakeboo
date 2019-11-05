// @flow
import React from 'react'

import { withAuth, withLoginRequired } from 'use-auth0-hooks'

import type { AuthType } from '../../../app/domain/Auth.type'

import AccountContainer from '../../../app/components/account/AccountContainer'

export const ProfileClass = ({ auth }: { auth: AuthType }) => {
  const { name } = auth.user

  return (
    <AccountContainer>
      <div id="profile">
        <h2>Hi {name}</h2>
      </div>
    </AccountContainer>
  )
}

export default withLoginRequired(withAuth(ProfileClass))
