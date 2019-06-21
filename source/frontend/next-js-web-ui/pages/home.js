// @flow
import React from 'react'

import type {Account} from '../app/domain/Account.type'

type HomeProps = {
  account: Account
}

class Home extends React.Component<HomeProps> {
  render() {
    const {account} = this.props;

    return (
        <div className='home'>
          <h1>Hello {account.displayName}</h1>
        </div>
    )
  }
}

export default Home
