// @flow
import { SagaIterator } from 'redux-saga'
import { all, put, takeLatest } from 'redux-saga/effects'

import gql from 'graphql-tag'
import getApolloClient from '../../apollo/getApolloClient'

import type { ResetPasswordAction } from '../action'
import { addErrorAlert, RESET_PASSWORD, resetPasswordSucceed } from '../action'

const mutation = gql`
  mutation {
    updatePassword(password: $password, confirmation: $confirmation)
  }
`

function* resetPassword(action: ResetPasswordAction) {
  const { password, confirmation } = action
  const variables = { password, confirmation }

  try {
    const response = yield getApolloClient().mutate({ mutation, variables })

    if (response.data && response.data.updatePassword) {
      yield put(resetPasswordSucceed())
    } else {
      // TODO: Failure
    }
  } catch (error) {
    if (error.networkError.statusCode) {
      yield put(addErrorAlert('GRAPHQL_ERROR'))
    } else {
      yield put(addErrorAlert('BACKEND_NOT_REACHABLE'))
    }
  }
}

function* resetPasswordSaga(): SagaIterator {
  yield takeLatest(RESET_PASSWORD, resetPassword)
}

export default function* userManagementSaga(): SagaIterator {
  yield all([resetPasswordSaga()])
}
