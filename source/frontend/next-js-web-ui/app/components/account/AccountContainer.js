// @flow
import * as React from 'react'

import ActiveMenuItem from '../layout/ActiveMenuItem'

type AccountContainerProps = {
  children?: React.Node
}

export class AccountContainer extends React.Component<AccountContainerProps> {
  render() {
    return (
        <div className='account grid-container'>
          <div className='grid-x'>
            <div className='cell medium-9 medium-offset-3'>
              <h1>Account</h1>
            </div>

            <div className='cell medium-2'>
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

            <div className='cell medium-1'/>

            <div className='cell medium-9'>
              {this.props.children}
            </div>
          </div>
        </div>
    )
  }
}

export default AccountContainer
