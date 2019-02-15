// @flow
import Token from '../../domain/session/Token'
import UserInfo from '../../domain/session/UserInfo'

import type {SessionAction} from '../actions'
import {SET_TOKEN, SET_USER_INFO} from '../actions'

export type SessionState = {
  +token: Token,
  +userInfo: UserInfo
}

export const initialSessionState: SessionState = {
  token: new Token(),
  userInfo: new UserInfo()
}

export default (state: SessionState = initialSessionState,
    action: SessionAction) => {
  switch (action.type) {
    case SET_TOKEN:
      return {
        ...state,
        token: action.token
      }
    case SET_USER_INFO:
      return {
        ...state,
        userInfo: action.userInfo
      }
    default:
      return state
  }
}
