// @flow
import React from 'react'

import Router from 'next/router'

require('./goodbye.scss')

const backToHome = () => {
  Router.push('/')
}

export const GoodbyeClass = () => {
  return (
    <div id="goodbye">
      <div className="container">
        <h1>Bye!</h1>
        <h4>
          <small>We hope to see you again soon..</small>
        </h4>
        <br />
        <button
          type="button"
          className="button"
          aria-label="Back to Qdrakeboo"
          onClick={backToHome}
        >
          Back to Qdrakeboo
        </button>
      </div>
    </div>
  )
}

export default GoodbyeClass
