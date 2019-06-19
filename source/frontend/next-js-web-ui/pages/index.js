// @flow
import React from 'react'

import Header from '../components/layout/Header'

type IndexProps = {}

class Index extends React.Component<IndexProps> {
  render() {
    return (
        <div className='index'>
          <Header/>

          <h1>Hello World</h1>
        </div>
    )
  }
}

export default Index
