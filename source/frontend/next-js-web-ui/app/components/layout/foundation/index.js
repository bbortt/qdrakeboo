// @flow
import React from 'react'

import { withAuth } from 'use-auth0-hooks'

const FoundationClass = ({ auth }) => {
  const { isLoading } = auth

  if (!isLoading && typeof window !== 'undefined') {
    require('foundation-sites')
    // $FlowFixMe
    $(document).foundation()
  }

  return null
}

export default withAuth(FoundationClass)
