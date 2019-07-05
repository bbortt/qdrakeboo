// @flow
import React from 'react'

import Link from 'next/link'
import ActiveMenuItem from './ActiveMenuItem'
import type {Account} from '../../domain/Account.type'

type HeaderProps = {
  account: Account
}

require('./header.scss')

const HIDE_DROPDOWN_EVENT = 'hide.zf.dropdownmenu';

class Header extends React.Component<HeaderProps> {

  constructor(props) {
    super(props)

    this.state = {
      counter: null
    }
  }

  componentDidMount() {
    $(window).on(HIDE_DROPDOWN_EVENT,
        () => setTimeout(() => this.setState({counter: new Date()}), 2000))
  }

  componentWillUnmount() {
    $(window).off(HIDE_DROPDOWN_EVENT)
  }

  render() {
    const {account} = this.props;

    if (!account) {
      return null
    }

    console.log(this.state.counter)

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
                  <Link href='/home' id='link-qdrakeboo'>
                    Qdrakeboo
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
                          href='/account'><a>Settings</a></ActiveMenuItem>
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
