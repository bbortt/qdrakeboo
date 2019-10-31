// @flow
import React from 'react'

import Router, { withRouter } from 'next/router'

import type { RouterType } from '../../../app/domain/Router.type'

const AUTOMATIC_REDIRECT = 'profile'

export const AccountClass = ({ router }: { router: RouterType }) => {
  const { pathname } = router

  if (typeof window !== 'undefined') {
    Router.push(`${pathname}/${AUTOMATIC_REDIRECT}`)
  }

  return null
}

export default withRouter(AccountClass)
