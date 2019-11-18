// @flow
import React from 'react'

import ActiveMenuItem from '../layout/header/ActiveMenuItem'

const AccountNavigationClass = () => (
  <div id="account-navigation" className="callout">
    <h3>Navigation</h3>

    <ul className="vertical menu">
      <li>
        <ActiveMenuItem href="profile">
          <a>Profile</a>
        </ActiveMenuItem>
      </li>
      <li>
        <ActiveMenuItem href="reset-password">
          <a>Reset Password</a>
        </ActiveMenuItem>
      </li>
    </ul>
  </div>
)

export default AccountNavigationClass
