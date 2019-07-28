// @flow
import React from 'react'

import {connect} from 'react-redux';

import AccountContainer from '../../app/components/account/AccountContainer'
import type {UserInfo} from '../../app/domain/UserInfo.type'

type ProfileProps = {
  userInfo: UserInfo,
}

export class Profile extends React.Component<ProfileProps> {
  render() {
    const {userInfo} = this.props

    return (
        <AccountContainer>
          <div className='profile'>
            <h2>Hi {userInfo.displayName}</h2>
          </div>
        </AccountContainer>
    )
  }
}

export default connect(({userInfo}) => userInfo)(Profile);
