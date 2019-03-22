// @flow
import UserInfo from '../../domain/session/UserInfo'

import type {SessionAction} from '../actions'
import {SET_USER_INFO} from '../actions'

export type SessionState = {
  +userInfo: UserInfo
}

export const initialSessionState: SessionState = {
  userInfo: new UserInfo()
}

export default (state: SessionState = initialSessionState,
                action: SessionAction) => {
  switch (action.type) {
    case SET_USER_INFO:
      return {
        ...state,
        userInfo: action.userInfo
      }
    default:
      return state
  }
}
