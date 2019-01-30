import {all} from 'redux-saga/effects'

import authenticationSaga from './authentication.saga'

export default function* rootSaga() {
  yield all([
    authenticationSaga()
  ])
}
