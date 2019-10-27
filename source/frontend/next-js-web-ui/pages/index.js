import React from 'react'

import Router, {useRouter} from 'next/router'

import {useAuth} from 'use-auth0-hooks'

const startApp = () => {
  Router.push('/app')
}

export const IndexClass = () => {
  const {pathname, query} = useRouter();
  const {isAuthenticated, login} = useAuth()

  return (
      <div className="index">
        <div className="container">
          <h1>Welcome to Qdrakeboo</h1>
          <br/>
          <button
              type="button"
              className="button"
              aria-label="Sign in"
              onClick={() => login()}
          >
            {isAuthenticated ? 'Start' : 'Sign In'}
          </button>
        </div>
      </div>
  )
}

export default IndexClass
