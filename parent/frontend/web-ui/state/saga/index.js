import {all, fork} from 'redux-saga/effects'

import {requestUserInfoSaga} from './session.saga'

export default function* rootSaga() {
  yield all([
    fork(requestUserInfoSaga)
  ])
}
