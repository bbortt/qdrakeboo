// @flow
import getConfig from 'next/config'

import axios from 'axios'

import { all, call, takeLatest } from 'redux-saga/effects'

import type { ResetPasswordAction } from '../action'
import { RESET_PASSWORD } from '../action'

const { publicRuntimeConfig } = getConfig()

// TODO: Graphql?
function* resetPassword(action: ResetPasswordAction) {
  const { password, confirmation } = action

  const requestConfig = { headers: {} }

  requestConfig.headers[
    publicRuntimeConfig.apiForwardToHeaderName
  ] = `${publicRuntimeConfig.apiUrl}/graphql`

  try {
    const response = yield call(
      axios.get,
      `${publicRuntimeConfig.publicUrl}/api`,
      requestConfig
    )

    if (response.status === 200) {
      console.log('response: ', response)
    }

    // TODO: Dispatch error
  } catch (error) {
    // TODO: Dispatch error
  }
}

function* resetPasswordSaga(): Iterable<any> {
  yield takeLatest(RESET_PASSWORD, resetPassword)
}

export default function* userManagementSaga(): Iterable<any> {
  yield all([resetPasswordSaga()])
}
