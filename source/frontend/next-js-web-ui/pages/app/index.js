import React from 'react'

import { withLoginRequired } from 'use-auth0-hooks'

const AppClass = () => (
  <div id="app">
    <h1>Welcome to Qdrakeboo</h1>
  </div>
)

export default withLoginRequired(AppClass)
