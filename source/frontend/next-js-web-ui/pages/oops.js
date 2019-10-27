// @flow
import React from 'react'

import { useRouter } from 'next/router'

export const OopsClass = () => {
  const router = useRouter()
  const { message } = router.query

  return (
    <div>
      <div className="container">
        <h1>Oops</h1>
        <br />
        <p>An error occured when signing in!</p>
        <pre>{message || 'Unknown Error'}</pre>
      </div>
    </div>
  )
}

export default OopsClass
