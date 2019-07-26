// @flow
import React from 'react';

import Router, {withRouter} from 'next/router'

import type {Context} from '../../app/domain/Context.type';

const AUTOMATIC_REDIRECT = 'profile';

type AccountProps = {}

class Account extends React.Component<AccountProps> {

  static async getInitialProps({ctx}: { ctx: Context }) {
    const {pathname, res} = ctx;

    if (res) {
      return res.redirect(`${pathname}/${AUTOMATIC_REDIRECT}`)
    }

    Router.push(`${pathname}/${AUTOMATIC_REDIRECT}`)
  }
}

export default Account
