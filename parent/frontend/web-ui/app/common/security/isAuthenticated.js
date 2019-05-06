// @flow
import axios from 'axios';

export default async (): boolean => {
  try {
    const response = call(axios.get, `${publicRuntimeConfig.publicApiUrl}/session/check`)

    return response.status === 200
  } catch (error) {
    return false
  }
}
