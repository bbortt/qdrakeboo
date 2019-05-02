// @flow
import React from 'react'
import PropTypes from 'prop-types'

import Router from 'next/router'

require('./index.scss')

class Index extends React.Component<Index.propTypes> {

  launch = () => {
    Router.push('/home')
  }

  render() {
    return (
        <div className='Index'>
          <div className='grid-container'>
            <div className='grid-x'>
              <div className='cell'>
                <h1>Welcome, stranger!</h1>
              </div>

              <div className='cell'>
                <button className='button'
                        onClick={this.launch}>{this.props.isAuthenticated
                    ? 'Launch' : 'Sign In'}</button>
              </div>
            </div>
          </div>
        </div>
    )
  }
}

Index.propTypes = {
  isAuthenticated: PropTypes.bool
}

export default Index
