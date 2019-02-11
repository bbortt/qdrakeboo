// @flow
import {Token} from 'client-oauth2';

import type {SessionAction} from '../actions';
import {SET_TOKEN} from '../actions';

export type SessionState = {
  +token: Token
}

export const initialSessionState: SessionState = {
  token: {}
}

export default (state: SessionState = initialSessionState,
    action: SessionAction) => {
  switch (action.type) {
    case SET_TOKEN:
      return {
        ...state,
        token: action.token
      }
    default:
      return state
  }
}
