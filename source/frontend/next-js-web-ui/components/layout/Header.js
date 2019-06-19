// @flow
import React from 'react'

type HeaderProps = {}

class Header extends React.Component<HeaderProps> {
  render() {
    return (
        <header>
          <div className="title-bar" data-hide-for="medium"
               data-responsive-toggle="main-menu-animated">
            <div data-toggle id="data-toggle-main-menu">
              <span className="show-for-sr">Open main-menu</span>
              <span
                  aria-hidden="true"></span>
            </div>
            <div class="title-bar-title">Menu</div>
          </div>

          <div class="top-bar hide-for-small-only">
            <div class="top-bar-left">
              <ul class="horizontal menu" data-responsive-menu="dropdown">
                /* TODO: Check router-link */
                <li /* routerLinkActive="is-active" */>
                  <a href="/">One</a>
                  <ul class="menu vertical">
                    <li><a href="#">One</a></li>
                    <li><a href="#">Two</a></li>
                    <li><a href="#">Three</a></li>
                  </ul>
                </li>
                <li><a href="#">Two</a></li>
                <li><a href="#">Three</a></li>
              </ul>
            </div>
          </div>

          <ul class="vertical menu show-for-small-only"
              data-animate="slide-in-down slide-out-up"
              data-responsive-menu="drilldown medium-dropdown"
              id="main-menu-animated">
            /* TODO: Check router-link */
            <li /* routerLinkActive="is-active" */>
              <a href="/">One</a>
              <ul class="menu vertical">
                <li><a href="#">One</a></li>
                <li><a href="#">Two</a></li>
                <li><a href="#">Three</a></li>
              </ul>
            </li>
            <li><a href="#">Two</a></li>
            <li><a href="#">Three</a></li>
          </ul>
        </header>
    )
  }
}

export default Header
