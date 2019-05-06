// @flow
import getConfig from 'next/config'

import axios from 'axios'
import type {NextContext} from "../axios/NextContext";

const {publicRuntimeConfig} = getConfig()

export default async (nextContext: NextContext): boolean => {
  const requestConfig = {}

  if (nextContext.isServer && nextContext.req.headers.cookie) {
    requestConfig.headers = {
      cookie: nextContext.req.headers.cookie
    }
  }
  try {
    const response = await axios.get(
        `${publicRuntimeConfig.publicUrl}/session/check`, requestConfig)

    return response.status === 200
  } catch (error) {
    return false
  }
}
