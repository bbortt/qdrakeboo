// @flow
import { SagaIterator } from 'redux-saga'
import { all, fork } from 'redux-saga/effects'

import userInfoSaga from './user-info.saga'
import userManagementSaga from './user-management.saga'

export default function* rootSaga(): SagaIterator {
  yield all([fork(userInfoSaga), fork(userManagementSaga)])
}
