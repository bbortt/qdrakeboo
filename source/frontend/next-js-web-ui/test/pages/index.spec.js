/* global describe, it */
import { shallow } from 'enzyme'

import React from 'react'
import { expect } from 'chai'

import { Index } from '../../pages'

describe('Index', () => {
  const index = shallow(<Index />)

  it('is DIVided', () => {
    expect(index.find('.index')).to.be.an('object')
  })

  it('has correct application title', () => {
    expect(index.find('h1').text()).to.equal('Welcome to Qdrakeboo')
  })

  it('has sign in button', () => {
    expect(index.find('button').text()).to.equal('Sign In')
  })
})
