// @flow
import React from 'react'

import {connect} from 'react-redux';

import AccountContainer from '../../app/components/account/AccountContainer'
import type {UserInfo} from '../../app/domain/UserInfo.type'

type SettingsProps = {
  userInfo: UserInfo,
}

class Settings extends React.Component<SettingsProps> {
  render() {
    const {userInfo} = this.props

    return (
        <AccountContainer>
          <div className='settings'>
            <h2>Hi {userInfo.displayName}</h2>
          </div>
        </AccountContainer>
    )
  }
}

export default connect(({userInfo}) => userInfo)(Settings);
