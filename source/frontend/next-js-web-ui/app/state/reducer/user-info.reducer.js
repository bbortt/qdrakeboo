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
};

const isAuthenticated = (userInfo: UserInfo,
    permissions: string[]): boolean => {
  return Object.entries(userInfo).length !== 0
      && userInfo.constructor === Object
      && permissions !== [];
}

export default (state: UserInfoState = initialUserInfoState,
    action: UserInfoAction): UserInfoState => {
  switch (action.type) {
    case SET_USER_INFO:
      const setUserInfoAction = ((action: any): SetUserInfoAction);
      const {userInfo} = setUserInfoAction;

      return {
        ...state,
        isAuthenticated: isAuthenticated(userInfo, state.permissions),
        userInfo: userInfo,
      };
    case SET_PERMISSIONS:
      const setPermissionsAction = ((action: any): SetPermissionsAction);
      const {permissions} = setPermissionsAction;

      return {
        ...state,
        isAuthenticated: isAuthenticated(state.userInfo, permissions),
        permissions: permissions,
      };
    default:
      return state
  }
}
