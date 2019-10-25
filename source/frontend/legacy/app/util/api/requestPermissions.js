// @flow
import getConfig from 'next/config'

import fetch from 'isomorphic-unfetch'

import type { Context } from '../../domain/Context.type'

const { publicRuntimeConfig } = getConfig()

export default async (nextContext: Context): Promise<string[] | number> => {
  const requestConfig = { redirect: 'manual', headers: {} }

  requestConfig.headers[
    publicRuntimeConfig.apiForwardToHeaderName
  ] = `${publicRuntimeConfig.apiUrl}/userinfo`

  if (nextContext.req && nextContext.req.headers.cookie) {
    requestConfig.headers.cookie = nextContext.req.headers.cookie
  }

  try {
    const response = await fetch(
      `${publicRuntimeConfig.publicUrl}/api`,
      requestConfig
    )

    if (response.status === 200) {
      const data = await response.json()
      return data.credentials.claims.permissions
    }

    return response.status
  } catch (error) {
    if (error.response) {
      return error.response.status
    }

    return error.message
  }
}
