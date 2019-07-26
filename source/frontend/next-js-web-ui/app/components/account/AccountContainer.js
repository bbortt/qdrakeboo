// @flow
import * as React from 'react'

import Link from 'next/link'

type AccountContainerProps = {
  children?: React.Node
}

class AccountContainer extends React.Component<AccountContainerProps> {
  render() {
    return (
        <div className='account grid-container'>
          <div className='grid-x'>
            <div className='cell small-9 small-offset-3'>
              <h1>Account</h1>
            </div>

            <div className='cell small-3'>
              <ul className="vertical menu">
                <li>
                  <Link href="settings">
                    <a>Profile</a>
                  </Link>
                </li>
                <li>
                  <Link href="reset-password">
                    <a>Reset Password</a>
                  </Link>
                </li>
              </ul>
            </div>

            <div className='cell small-9'>
              {this.props.children}
            </div>
          </div>
        </div>
    )
  }
}

export default AccountContainer
