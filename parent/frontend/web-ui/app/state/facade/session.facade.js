// @flow
import type {ReduxState} from '../reducer'

import type {UserInfo} from '../../domain/session/UserInfo'

export const getUserInfo = (state: ReduxState): UserInfo => state.session.userInfo
