import {all, fork} from 'redux-saga/effects'

import {
  requestSessionSaga,
  requestUserInfoSaga
} from './session.saga'

export default function* rootSaga() {
  yield all([
    fork(requestSessionSaga),
    fork(requestUserInfoSaga)
  ])
}
