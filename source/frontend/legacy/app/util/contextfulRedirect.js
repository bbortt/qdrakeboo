// @flow
import Router from 'next/router'

import type { Context } from '../domain/Context.type'

export default (nextContext: Context, dest: string, queryParams: ?any) => {
  const { res } = nextContext

  let queryArgument = ''

  if (queryParams) {
    const esc = encodeURIComponent
    queryArgument += Object.keys(queryParams)
      .map((key: string) => `${esc(key)}=${esc(queryParams[key])}`)
      .join('&')
  }

  const uri = `${dest}${queryParams ? `?${queryArgument}` : ''}`

  if (res) {
    return res.redirect(uri)
  }

  return Router.push(uri)
}
