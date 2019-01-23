/* global describe, it */
import {shallow} from 'enzyme'
import React from 'react'
import expect from 'expect.js'

import App from '../pages/index.js'

describe('App', () => {
  it('renders"', () => {
    const app = shallow(<App/>)

    expect(app.text()).to.equal('')
  })
})
