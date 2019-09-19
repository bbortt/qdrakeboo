// @flow
import { SagaIterator } from 'redux-saga'
import { all, takeLatest } from 'redux-saga/effects'

import gql from 'graphql-tag'
import getApolloClient from '../../apollo/getApolloClient'

import type { ResetPasswordAction } from '../action'
import { RESET_PASSWORD } from '../action'

const query = gql`
  query {
    getAllMovies {
      name
    }
  }
`

// TODO: Graphql?
function* resetPassword(action: ResetPasswordAction) {
  const { password, confirmation } = action

  try {
    const response = yield getApolloClient().query({ query })

    console.log('apollo res: ', response)

    // TODO: Dispatch error
  } catch (error) {
    // TODO: Dispatch error

    console.log('error: ', error)
  }
}

function* resetPasswordSaga(): SagaIterator {
  yield takeLatest(RESET_PASSWORD, resetPassword)
}

export default function* userManagementSaga(): SagaIterator {
  yield all([resetPasswordSaga()])
}
