// @flow
import React from 'react'

import { connect } from 'react-redux'

import Link from 'next/link'
import ActiveMenuItem from './ActiveMenuItem'

type HeaderProps = {
  +isAuthenticated: boolean,
  +online: boolean,
  +permissions: string[],
}

require('./header.scss')

export class HeaderClass extends React.Component<HeaderProps> {
  render() {
    const { isAuthenticated, online, permissions } = this.props

    if (!isAuthenticated && online) {
      return null
    }

    return (
      <header>
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
                    <ActiveMenuItem href="app/account">
                      <a>Settings</a>
                    </ActiveMenuItem>
                  </li>
                  <li>
                    <ActiveMenuItem href="/logout">
                      <a>Logout</a>
                    </ActiveMenuItem>
                  </li>
                </ul>
              </li>
            </ul>
          </div>
        </div>
      </header>
    )
  }
}

export default connect(({ health, userInfo }) => {
  return {
    isAuthenticated: userInfo.isAuthenticated,
    online: health.online,
    permissions: userInfo.permissions,
  }
})(HeaderClass)
