// @flow
import { all, fork } from 'redux-saga/effects'

import userInfoSaga from './user-info.saga'

export default function* rootSaga(): Iterable<any> {
  yield all([fork(userInfoSaga)])
}
