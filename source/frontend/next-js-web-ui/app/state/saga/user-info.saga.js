// @flow
import { all, put, takeLatest } from 'redux-saga/effects'

import type { CompleteUserInfoAction } from '../action'
import { COMPLETE_USER_INFO, setPermissions, setUserInfo } from '../action'

function* completeUserInfo(action: CompleteUserInfoAction) {
  const { userInfo, permissions } = action

  yield put(setUserInfo(userInfo))
  yield put(setPermissions(permissions))
}

function* completeUserInfoSaga(): Iterable<any> {
  yield takeLatest(COMPLETE_USER_INFO, completeUserInfo)
}

export default function* userInfoSaga(): Iterable<any> {
  yield all([completeUserInfoSaga()])
}
