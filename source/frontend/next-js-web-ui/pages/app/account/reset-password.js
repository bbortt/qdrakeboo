// @flow
import React from 'react'

import Head from 'next/head'

import getStore from '../../../app/getStore'

import { withAuth, withLoginRequired } from 'use-auth0-hooks'

import axios from 'axios'
import { syncWellKnown } from '../../../app/util/loadWellKnown'

import type { AuthType } from '../../../app/domain/Auth.type'

import AccountContainer from '../../../app/components/account/AccountContainer'
import { addErrorAlert, addSuccessAlert } from '../../../app/state/action'

const resetPassword = (email: string) => {
  const store = getStore()
  const { auth0 } = syncWellKnown()

  axios
    .post(`https://${auth0.domain}/dbconnections/change_password`, {
      client_id: auth0.clientId,
      email: email,
      connection: 'Username-Password-Authentication',
    })
    .then(() =>
      store.dispatch(
        addSuccessAlert(
          'Successfully send a password-reset link!' /* TODO: i18n code title */
        )
      )
    )
    .catch(error =>
      store.dispatch(addErrorAlert(error.message /* TODO: i18n code title */))
    )
}

export const ResetPasswordClass = ({ auth }: { auth: AuthType }) => {
  const { email } = auth.user

  return (
    <AccountContainer>
      <Head>
        <title>Qdrakeboo | Reset Password</title>
      </Head>

      <div id="resetpassword">
        <h2>
          <small>Reset Password</small>
        </h2>
        <p>
          We will send you a confirmation e-mail containing a link to reset your
          password.
        </p>
        <button
          type="button"
          className="button"
          aria-label="Reset password"
          onClick={() => resetPassword(email)}
        >
          Reset Password
        </button>
      </div>
    </AccountContainer>
  )
}

export default withLoginRequired(withAuth(ResetPasswordClass))
