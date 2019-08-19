/* global describe, it */
import { shallow } from 'enzyme'

import React from 'react'
import { expect } from 'chai'

import { HomeClass } from '../../pages/home'

describe('Home', () => {
  const home = shallow(<HomeClass />)

  it('is DIVided', () => {
    expect(home.find('.home')).to.be.an('object')
  })

  it('says hello', () => {
    expect(home.find('h1').text()).to.equal('Welcome to Qdrakeboo')
  })
})
