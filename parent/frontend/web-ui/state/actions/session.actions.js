// @flow
import type {UserInfo} from '../../domain/session/UserInfo'
import type {NextContext} from '../../lib/axios/NextContext'

export const REQUEST_USER_INFO: string = 'Session: Request User-Info'
export const SET_USER_INFO: string = 'Session: Set User-Info'

export type RequestUserInfoAction = { type: string, nextContext: NextContext }
export type SetUserInfoAction = { type: string, userInfo: UserInfo }

export type SessionAction =
  RequestUserInfoAction |
  SetUserInfoAction

export function requestUserInfo(nextContext: NextContext): RequestUserInfoAction {
  return {type: REQUEST_USER_INFO, nextContext: nextContext}
}

export function setUserInfo(userInfo: UserInfo): SetUserInfoAction {
  return {type: SET_USER_INFO, userInfo: userInfo}
}
