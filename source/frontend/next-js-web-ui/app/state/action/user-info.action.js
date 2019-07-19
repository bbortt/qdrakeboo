// @flow
import type {Context} from '../../domain/Context.type'
import type {UserInfo} from '../../domain/UserInfo.type'

export const COMPLETE_USER_INFO: string = 'User-Info: Completion request'
export const SET_USER_INFO: string = 'User-Info: Set'
export const REQUEST_PERMISSIONS: string = 'User-Info: Request permissions'
export const SET_PERMISSIONS: string = 'User-Info: Request permissions succeed'
export const REQUEST_PERMISSIONS_FAILED: string = 'User-Info: Request permissions failed'

export type CompleteUserInfoAction = { type: string, nextContext: Context }
export type SetUserInfoAction = { type: string, userInfo: UserInfo }
export type RequestPermissionsAction = { type: string, nextContext: Context }
export type SetPermissionsAction = { type: string, permissions: string[] }
export type RequestPermissionsFailedAction = { type: string, error: any }

export type UserInfoAction =
    CompleteUserInfoAction |
    SetUserInfoAction |
    RequestPermissionsAction |
    SetPermissionsAction |
    RequestPermissionsFailedAction

export const completeUserInfo = (nextContext: Context) => {
  return {type: COMPLETE_USER_INFO, nextContext: nextContext}
}

export const setUserInfo = (userInfo: UserInfo): SetUserInfoAction => {
  return {type: SET_USER_INFO, userInfo: userInfo}
}

export const requestPermissions = (nextContext: Context) => {
  return {type: REQUEST_PERMISSIONS, nextContext: nextContext}
}

export const setPermissions = (permissions: string[]): SetPermissionsAction => {
  return {type: SET_PERMISSIONS, permissions: permissions}
}

export const requestPermissionsFailed = (error: any): RequestPermissionsFailedAction => {
  return {type: REQUEST_PERMISSIONS_FAILED, error: error}
}
