// @flow
import {all, fork} from 'redux-saga/effects'

import {completeUserInfoSaga, requestPermissionsSaga} from './user-info.saga'

export default function* rootSaga(): Iterable<any> {
  yield all([
    fork(completeUserInfoSaga),
    fork(requestPermissionsSaga)
  ])
}
