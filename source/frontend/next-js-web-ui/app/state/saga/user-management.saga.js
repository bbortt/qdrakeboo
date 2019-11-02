// @flow
import { SagaIterator } from 'redux-saga'
import { all, put, takeLatest } from 'redux-saga/effects'

import gql from 'graphql-tag'
import getApolloClient from '../../apollo/getApolloClient'

import type { ResetPasswordAction } from '../action'
import {
  addErrorAlert,
  addSuccessAlert,
  RESET_PASSWORD,
  RESET_PASSWORD_SUCCEED,
  resetPasswordFailed,
  resetPasswordSucceed as resetPasswordSucceedAction,
} from '../action'

const mutation = gql`
  mutation UpdatePassword($password: String!, $confirmation: String!) {
    updatePassword(password: $password, confirmation: $confirmation)
  }
`

function* resetPassword(action: ResetPasswordAction) {
  const { password, confirmation } = action
  const variables = { password, confirmation }

  try {
    yield getApolloClient().mutate({ mutation, variables })

    yield put(resetPasswordSucceedAction())
  } catch (error) {
    const errorCode = error.graphQLErrors[0].extensions.code

    if (errorCode === 'BAD_USER_INPUT') {
      const message =
        error.message.indexOf(':') !== 0
          ? error.message.split(':')[1].trim()
          : error.message

      yield put(resetPasswordFailed(message))
    } else {
      yield put(addErrorAlert(errorCode /* TODO: i18n code title */))
    }
  }
}

function* resetPasswordSaga(): SagaIterator {
  yield takeLatest(RESET_PASSWORD, resetPassword)
}

function* resetPasswordSucceed() {
  yield put(addSuccessAlert('RESET_PASSWORD_SUCCEED'))
}

function* resetPasswordSucceedSaga(): SagaIterator {
  yield takeLatest(RESET_PASSWORD_SUCCEED, resetPasswordSucceed)
}

export default function* userManagementSaga(): SagaIterator {
  yield all([resetPasswordSaga(), resetPasswordSucceedSaga()])
}
