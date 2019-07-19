// @flow
import React from 'react'

import Link from 'next/link'
import ActiveMenuItem from './ActiveMenuItem'
import type {UserInfo} from '../../domain/UserInfo.type'

type HeaderProps = {
  userInfo: UserInfo
}

require('./header.scss')

class Header extends React.Component<HeaderProps> {

  render() {
    const {userInfo} = this.props;

    if (!userInfo) {
      return null
    }

    return (
        <header>
          <div className='title-bar'
               data-responsive-toggle='qdrakeboo-main-menu'
               data-hide-for='medium' style={{display: 'none'}}>
            <button className='menu-icon' type='button'
                    data-toggle='qdrakeboo-main-menu'></button>
            <div className='title-bar-title'>Menu</div>
          </div>

          <div className='top-bar' id='qdrakeboo-main-menu'
               data-animate='fade-in fade-out' style={{display: 'none'}}>
            <div className='top-bar-left'>
              <ul className='menu'>
                <li className='menu-text'>
                  <Link href='/home'>
                    <a>Qdrakeboo</a>
                  </Link>
                </li>

                <li><input type='search' placeholder='Search'/></li>
                <li>
                  <button type='button' className='button'>Search</button>
                </li>
              </ul>
            </div>

            <div className='top-bar-right'>
              <ul className='dropdown menu' data-dropdown-menu>
                <li>
                  <a>Account</a>
                  <ul className='menu vertical'>
                    <li>
                      <ActiveMenuItem
                          href='/account/settings'><a>Settings</a></ActiveMenuItem>
                    </li>
                    <li>
                      <ActiveMenuItem
                          href='/logout'><a>Logout</a></ActiveMenuItem>
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

export default Header
