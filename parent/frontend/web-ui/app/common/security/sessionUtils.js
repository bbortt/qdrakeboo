// @flow
import axios from 'axios';

import type {UserInfo} from '../../domain/session/UserInfo';
import type {NextContext} from '../axios/NextContext';

export const requestUserInfo = async (nextContext: NextContext): UserInfo => {
  const requestConfig = {}

  if (nextContext.isServer && nextContext.req.headers.cookie) {
    requestConfig.headers = {
      cookie: nextContext.req.headers.cookie
    }
  }

  try {
    const response = await axios.get(`${publicRuntimeConfig.publicApiUrl}/user`,
        requestConfig)

    return await response.json()
  } catch (error) {
    return {}
  }
}
