// @flow
import type { UserInfo } from '../../domain/UserInfo.type'

export const COMPLETE_USER_INFO: string = 'User-Info: Completion request'
export const SET_USER_INFO: string = 'User-Info: Set'
export const SET_PERMISSIONS: string = 'User-Info: Request permissions succeed'

export type CompleteUserInfoAction = {
  type: string,
  userInfo: UserInfo,
  permissions: string[],
}
export type SetUserInfoAction = { type: string, userInfo: UserInfo }
export type SetPermissionsAction = { type: string, permissions: string[] }

export type UserInfoAction =
  | CompleteUserInfoAction
  | SetUserInfoAction
  | SetPermissionsAction

export const completeUserInfo = (userInfo: UserInfo, permissions: string[]) => {
  return { type: COMPLETE_USER_INFO, userInfo, permissions }
}

export const setUserInfo = (userInfo: UserInfo): SetUserInfoAction => {
  return { type: SET_USER_INFO, userInfo }
}

export const setPermissions = (permissions: string[]): SetPermissionsAction => {
  return { type: SET_PERMISSIONS, permissions }
}
