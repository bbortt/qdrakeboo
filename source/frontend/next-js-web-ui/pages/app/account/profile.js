// @flow
import React from 'react'

import Head from 'next/head'

import { withAuth, withLoginRequired } from 'use-auth0-hooks'

import { faCheckCircle, faTimesCircle } from '@fortawesome/free-solid-svg-icons'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'

import type { AuthType } from '../../../app/domain/Auth.type'

import AccountContainer from '../../../app/components/account/AccountContainer'

require('./profile.scss')

export const ProfileClass = ({ auth }: { auth: AuthType }) => {
  const { user } = auth

  return (
    <AccountContainer>
      <Head>
        <title>Qdrakeboo | Manage Account</title>
      </Head>

      <div id="profile">
        <div className="grid-x">
          <div className="cell media-object grid-x">
            <div className="media-object-section cell small-3 medium-2">
              <div className="thumbnail">
                <img src={user.picture} alt="Profile Picture" />
              </div>
            </div>
            <div className="media-object-section cell small-8 medium-9 small-offset-1 container-align-middle">
              <h2>{user.name}</h2>
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

          <div className="cell">
            {user.email_verified ? (
              <p>
                <FontAwesomeIcon icon={faCheckCircle} className="success" />
                &nbsp;E-Mail verified!
              </p>
            ) : (
              <p>
                <FontAwesomeIcon icon={faTimesCircle} className="error" />
                &nbsp;E-Mail not verified!
              </p>
            )}
          </div>
        </div>
      </div>
    </AccountContainer>
  )
}

export default withLoginRequired(withAuth(ProfileClass))
