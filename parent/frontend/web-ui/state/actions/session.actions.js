// @flow
import UserInfo from '../../domain/session/UserInfo'

export const REQUEST_USER_INFO: string = 'Session: Request User-Info'
export const SET_USER_INFO: string = 'Session: Set User-Info'

export type RequestUserInfoAction = { type: string }
export type SetUserInfoAction = { type: string, userInfo: UserInfo }

export type SessionAction =
  RequestUserInfoAction |
  SetUserInfoAction

export function requestUserInfo(): RequestUserInfoAction {
  return {type: REQUEST_USER_INFO}
}

export function setUserInfo(userInfo: UserInfo): SetUserInfoAction {
  return {type: SET_USER_INFO, userInfo: userInfo}
}
