// @flow
import React from 'react'
import type { Node } from 'react'

import AccountNavigation from './AccountNavigation'

require('./account-container.scss')

export const AccountContainerClass = ({ children }: { children: Node }) => (
  <div id="account" className="grid-container">
    <div className="grid-x grid-padding-x">
      <div className="cell small-3">
        <AccountNavigation />
      </div>

      <div className="cell small-9">
        <div className="grid-y">
          <div className="cell">
            <h1>Account Management</h1>
          </div>

          <div className="cell " />

          <div className="cell">{children}</div>
        </div>
      </div>
    </div>
  </div>
)

export default AccountContainerClass
