// @flow
import React from 'react'

import { useAuth } from 'use-auth0-hooks'

require('./index.scss')

export const IndexClass = () => {
  const { isAuthenticated, isLoading, login } = useAuth()

  return (
    <div id="index">
      <div className="container">
        <h1>Qdrakeboo</h1>
        <h4>
          <small>The streaming service for geeks!</small>
        </h4>
        <br />
        <button
          type="button"
          className="button"
          aria-label="Sign in"
          onClick={isLoading ? () => {} : login}
        >
          {isAuthenticated ? 'Start' : 'Sign In'}
        </button>
      </div>
    </div>
  )
}

export default IndexClass
