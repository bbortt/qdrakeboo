// @flow
import React from 'react'
import type { Node } from 'react'

import AccountNavigation from './AccountNavigation'

export const AccountContainerClass = ({ children }: { children: Node }) => (
  <div className="account grid-container">
    <div className="grid-x">
      <div className="cell medium-9 medium-offset-3">
        <h1>Account</h1>
      </div>

      <div className="cell medium-2">
        <AccountNavigation />
      </div>

      <div className="cell medium-1" />

      <div className="cell medium-9">{children}</div>
    </div>
  </div>
)

export default AccountContainerClass
