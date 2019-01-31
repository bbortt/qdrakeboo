// @flow
import type {SessionAction} from '../actions';
import {SESSION_REQUEST_FAILED, SESSION_REQUEST_SUCCEED} from '../actions';

export type SessionState = {
  +isAuthenticated: boolean,
  +user: any,
  +oauth2: any,
  +requested: boolean
}

export const initialSessionState: SessionState = {
  isAuthenticated: false,
  user: {},
  oauth2: {},
  requested: false
}

export default (state: SessionState = initialSessionState,
    action: SessionAction) => {
  switch (action.type) {
    case SESSION_REQUEST_FAILED:
      return {
        ...state,
        isAuthenticated: false,
        requested: true
      }
    case SESSION_REQUEST_SUCCEED:
      return {
        ...state,
        isAuthenticated: true,
        oauth2: action.authentication,
        requested: true
      }
    default:
      return state
  }
}
