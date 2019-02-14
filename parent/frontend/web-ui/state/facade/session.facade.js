// @flow
import type {ReduxState} from '../reducer';

import Token from '../../domain/session/Token';
import UserInfo from '../../domain/session/UserInfo';

export const getToken: Token = (state: ReduxState) => state.session.token
export const getUserInfo: UserInfo = (state: ReduxState) => state.session.userInfo
