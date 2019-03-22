// @flow
import type {ReduxState} from '../reducer'

import UserInfo from '../../domain/session/UserInfo'

export const getUserInfo = (state: ReduxState): UserInfo => state.session.userInfo
