// @flow
import React from 'react'

import ActiveMenuItem from '../layout/header/ActiveMenuItem'

import updateFoundation from '../../util/updateFoundation'

type AccountNavigationClassProps = {}

const accountMenu = () => (
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
)

require('./account-navigation.scss')

class AccountNavigationClass extends React.Component<AccountNavigationClassProps> {
  componentDidMount() {
    updateFoundation('#account-navigation')
  }

  render() {
    return (
      <div id="account-navigation">
        <ul
          className="accordion show-for-small-only"
          data-accordion
          data-allow-all-closed="true"
        >
          <li className="accordion-item" data-accordion-item>
            <a className="accordion-title">
              <h3>Navigation</h3>
            </a>

            <div className="accordion-content" data-tab-content>
              {accountMenu()}
            </div>
          </li>
        </ul>

        <div className="callout show-for-medium">
          <h3>Navigation</h3>

          {accountMenu()}
        </div>
      </div>
    )
  }
}

export default AccountNavigationClass
