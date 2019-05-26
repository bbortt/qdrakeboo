/* global describe, it */
import {shallow} from 'enzyme'
import React from 'react'
import {expect} from 'chai'

import App from '../pages/index.js'

describe('App', () => {
  it('renders', () => {
    const app = shallow(<App/>)

    expect(app).to.be.an('object')
  })
})
