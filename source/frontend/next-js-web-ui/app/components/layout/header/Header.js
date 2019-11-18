// @flow
import React from 'react'

import Link from 'next/link'
import { withRouter } from 'next/router'

import { withAuth } from 'use-auth0-hooks'

import type { AuthType } from '../../../domain/Auth.type'
import type { RouterType } from '../../../domain/Router.type'

import updateFoundation from '../../../util/updateFoundation'

import ActiveMenuItem from './ActiveMenuItem'

require('./header.scss')

export const HeaderClass = ({
  auth,
  router,
}: {
  auth: AuthType,
  router: RouterType,
}) => {
  const { pathname } = router
  const { isAuthenticated, logout } = auth

  if (pathname !== '/' && isAuthenticated) {
    updateFoundation('#nav-header')
  }

  return (
    <header id="nav-header">
      <div
        className="title-bar"
        data-responsive-toggle="qdrakeboo-main-menu"
        data-hide-for="medium"
        style={{ display: 'none' }}
      >
        <button
          type="button"
          className="menu-icon"
          aria-label="Open menu"
          data-toggle="qdrakeboo-main-menu"
        />
        <div className="title-bar-title">Menu</div>
      </div>

      <div
        className="top-bar"
        id="qdrakeboo-main-menu"
        data-animate="fade-in fade-out"
        style={{ display: 'none' }}
      >
        <div className="top-bar-left">
          <ul className="menu">
            <li className="menu-text">
              <Link href="/app">
                <a>Qdrakeboo</a>
              </Link>
            </li>

            <li>
              <input type="search" placeholder="Search" />
            </li>
            <li>
              <button
                type="button"
                className="button"
                aria-label="Search movies"
              >
                Search
              </button>
            </li>
          </ul>
        </div>

        <div className="top-bar-right">
          <ul className="dropdown menu" data-dropdown-menu>
            <li>
              <a>Account</a>
              <ul className="menu vertical">
                <li>
                  <ActiveMenuItem href="/app/account">
                    <a>Settings</a>
                  </ActiveMenuItem>
                </li>
                <li>
                  <button type="button" aria-label="Logout" onClick={logout}>
                    <a>Logout</a>
                  </button>
                </li>
              </ul>
            </li>
          </ul>
        </div>
      </div>
    </header>
  )
}

export default withRouter(withAuth(HeaderClass))
