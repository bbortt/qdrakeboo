// @flow
import { SagaIterator } from 'redux-saga'
import { all, put, takeLatest } from 'redux-saga/effects'

import type { CompleteUserInfoAction } from '../action'
import { COMPLETE_USER_INFO, setPermissions, setUserInfo } from '../action'

function* completeUserInfo(action: CompleteUserInfoAction) {
  const { userInfo, permissions } = action

  if (userInfo) {
    yield put(setUserInfo(userInfo))
    yield put(setPermissions(permissions))
  }
}

function* completeUserInfoSaga(): SagaIterator {
  yield takeLatest(COMPLETE_USER_INFO, completeUserInfo)
}

export default function* userInfoSaga(): SagaIterator {
  yield all([completeUserInfoSaga()])
}
