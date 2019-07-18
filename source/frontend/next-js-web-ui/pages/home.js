// @flow
import React from 'react'

import type { Account } from '../app/domain/Account.type'

type HomeProps = {}

class Home extends React.Component<HomeProps> {
  render() {
    return (
      <div className="home">
        <h1>Welcome to Qdrakeboo</h1>
      </div>
    )
  }
}

export default Home
