// @flow
import type {SessionAction} from '../actions';
import {
  SESSION_REQUEST,
  SESSION_REQUEST_FAILED,
  SESSION_REQUEST_SUCCEED
} from '../actions';

export type SessionState = {
  +user: any,
  +oauth2: any,
  +loading: boolean
}

export const initialSessionState: SessionState = {
  user: {},
  oauth2: {},
  loading: false
}

export default (state: SessionState = initialSessionState,
    action: SessionAction) => {
  switch (action.type) {
    case SESSION_REQUEST:
      return {
        ...state,
        loading: true
      }
    case SESSION_REQUEST_FAILED:
      return {
        ...state,
        oauth2: {},
        loading: false
      }
    case SESSION_REQUEST_SUCCEED:
      return {
        ...state,
        oauth2: action.authentication,
        loading: false
      }
    default:
      return state
  }
}
