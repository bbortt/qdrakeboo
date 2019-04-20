// @flow
import React from 'react'
import Router from 'next/router'

import {connect} from 'react-redux'

require('./index.scss')

class Index extends React.Component<Index.propTypes> {

  signIn = () => {
    Router.push('/session/renew')
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
              <button className='button' onClick={this.signIn}>Sign In</button>
            </div>
          </div>
        </div>
      </div>
    )
  }
}

export default connect(state => state.session)(Index)
