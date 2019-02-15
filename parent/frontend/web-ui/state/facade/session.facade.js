// @flow
import type {ReduxState} from '../reducer'

import Token from '../../domain/session/Token'
import UserInfo from '../../domain/session/UserInfo'

export const getToken = (state: ReduxState): Token => state.session.token
export const getUserInfo = (state: ReduxState): UserInfo => state.session.userInfo
