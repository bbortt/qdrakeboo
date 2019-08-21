// @flow
import getConfig from 'next/config'

import axios from 'axios'

import type { Context } from '../../domain/Context.type'

const { publicRuntimeConfig } = getConfig()

export default async (nextContext: Context): Promise<string[] | string> => {
  const requestConfig = { maxRedirects: 0, headers: {} }

  requestConfig.headers[
    publicRuntimeConfig.apiForwardToHeaderName
  ] = `${publicRuntimeConfig.apiUrl}/userinfo`

  if (nextContext.req && nextContext.req.headers.cookie) {
    requestConfig.headers.cookie = nextContext.req.headers.cookie
  }

  try {
    const response = await axios.get(
      `${publicRuntimeConfig.publicUrl}/api`,
      requestConfig
    )

    if (response.status === 200) {
      return response.data.credentials.claims.permissions
    }

    return response.status
  } catch (error) {
    return error.response.status
  }
}
