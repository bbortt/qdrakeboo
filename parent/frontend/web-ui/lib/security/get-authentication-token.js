// @flow
import nextCookie from 'next-cookies'

import Token from '../../domain/session/Token'

export default (context): Token => {
  const {token} = nextCookie(context)

  if (token) {
    return JSON.parse(token)
  }

  return token
}
