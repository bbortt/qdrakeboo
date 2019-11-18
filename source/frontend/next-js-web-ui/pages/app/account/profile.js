// @flow
import React from 'react'

import Head from 'next/head'

import { withAuth, withLoginRequired } from 'use-auth0-hooks'

import type { AuthType } from '../../../app/domain/Auth.type'

import AccountContainer from '../../../app/components/account/AccountContainer'

export const ProfileClass = ({ auth }: { auth: AuthType }) => {
  const { user } = auth
  console.log('user: ', user)

  return (
    <AccountContainer>
      <Head>
        <title>Qdrakeboo | Manage Account</title>
      </Head>

      <div id="profile">
        <div className="grid-x">
          <div className="cell">
            <div className="media-object row align-middle">
              <div className="media-object-section columns">
                <div className="thumbnail">
                  <img src={user.picture} alt="Profile Picture" />
                </div>
              </div>
              <div className="media-object-section columns">
                <h2>{user.name}</h2>
              </div>
            </div>
          </div>

          <div className="cell">
            <label htmlFor="sub">
              Unique id:
              <input
                id="sub"
                name="sub"
                type="text"
                placeholder="Unique id"
                value={user.sub}
                disabled
              />
            </label>
          </div>

          <div className="cell">
            <label htmlFor="nickname">
              Nickname:
              <input
                id="nickname"
                name="nickname"
                type="text"
                placeholder="Nickname"
                value={user.nickname}
                disabled
              />
            </label>
          </div>

          <div className="cell">
            <label htmlFor="email">
              Registered E-Mail:
              <input
                id="email"
                name="email"
                type="email"
                placeholder="Profile E-Mail"
                value={user.email}
                disabled
              />
            </label>
          </div>
        </div>
      </div>
    </AccountContainer>
  )
}

export default withLoginRequired(withAuth(ProfileClass))
