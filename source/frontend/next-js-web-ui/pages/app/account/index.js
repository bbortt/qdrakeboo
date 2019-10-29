// @flow
import React from 'react'

import Router, { withRouter } from 'next/router'

const AUTOMATIC_REDIRECT = 'profile'

export const AccountClass = ({ router }) => {
  const { pathname } = router

  if (typeof window !== 'undefined') {
    Router.push(`${pathname}/${AUTOMATIC_REDIRECT}`)
  }

  return null
}

export default withRouter(AccountClass)
