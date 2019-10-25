// @flow
import React from 'react'

import contextfulRedirect from '../../../app/util/contextfulRedirect'

import type { Context } from '../../../app/domain/Context.type'

const AUTOMATIC_REDIRECT = 'profile'

type AccountProps = {}

class AccountClass extends React.Component<AccountProps> {
  static async getInitialProps({ ctx }: { ctx: Context }) {
    const { pathname } = ctx

    return contextfulRedirect(ctx, `${pathname}/${AUTOMATIC_REDIRECT}`)
  }

  render() {
    return null
  }
}

export default AccountClass
