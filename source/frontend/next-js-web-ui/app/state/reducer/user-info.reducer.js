// @flow
import type {UserInfo} from '../../domain/UserInfo.type'

import type {
  SetPermissionsAction,
  SetUserInfoAction,
  UserInfoAction
} from '../action';
import {SET_PERMISSIONS, SET_USER_INFO} from '../action'

export type UserInfoState = {
  +isAuthenticated: boolean,
  +userInfo: UserInfo,
  +permissions: string[]
}

export const initialUserInfoState: UserInfoState = {
  isAuthenticated: false,
  userInfo: {},
  permissions: []
}

export default (state: UserInfoState = initialUserInfoState,
    action: UserInfoAction): UserInfoState => {
  switch (action.type) {
    case SET_USER_INFO:
      const setUserInfoAction = ((action: any): SetUserInfoAction)

      return {
        ...state,
        userInfo: setUserInfoAction.userInfo,
      }
    case SET_PERMISSIONS:
      const setPermissionsAction = ((action: any): SetPermissionsAction)

      return {
        ...state,
        isAuthenticated: true,
        permissions: setPermissionsAction.permissions,
      }
    default:
      return state
  }
}
