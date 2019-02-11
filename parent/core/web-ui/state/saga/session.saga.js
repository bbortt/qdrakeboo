// @flow
import {call, takeLatest} from 'redux-saga/effects';

import axios from 'axios'

import type {SetTokenAction} from '../actions';
import {SET_TOKEN} from '../actions'

import Token from '../../domain/session/Token';

function* setTokenSaga(action: SetTokenAction) {
  yield call(setAxiosAuthorization, action.token)
}

function* setAxiosAuthorization(token: Token) {
  axios.defaults.headers.common['Authorization'] = `${token.token_type}: ${token.access_token}`
}

export default function* sessionSaga(): Iterable<any> {
  yield takeLatest(SET_TOKEN, setTokenSaga)
}
