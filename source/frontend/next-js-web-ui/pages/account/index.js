// @flow
import React from 'react';

import Router from 'next/router'

import type {Context} from '../../app/domain/Context.type';

const AUTOMATIC_REDIRECT = 'profile';

type AccountProps = {}

class Account extends React.Component<AccountProps> {

  static async getInitialProps({ctx}: { ctx: Context }) {
    const {isServer, req} = ctx;

    if (isServer) {
      return req.redirect(AUTOMATIC_REDIRECT)
    }

    Router.push(AUTOMATIC_REDIRECT)
  }
}

export default Account
