// @flow
import { SagaIterator } from 'redux-saga'
import { all, fork } from 'redux-saga/effects'

import { polyfill } from 'es6-promise'
import 'isomorphic-unfetch'

import userInfoSaga from './user-info.saga'
import userManagementSaga from './user-management.saga'

polyfill()

export default function* rootSaga(): SagaIterator {
  yield all([fork(userInfoSaga), fork(userManagementSaga)])
}
