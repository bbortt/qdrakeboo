// @flow
import React, { Children } from 'react'

import Link from 'next/link'
import { withRouter } from 'next/router'

require('./active-menu-item.scss')

export default withRouter(({ router, children, as, href, ...rest }) => (
  <Link {...rest} href={href} as={as}>
    {React.cloneElement(Children.only(children), {
      className:
        router.pathname.includes(href) || router.pathname.includes(as)
          ? 'is-active'
          : '',
    })}
  </Link>
))
