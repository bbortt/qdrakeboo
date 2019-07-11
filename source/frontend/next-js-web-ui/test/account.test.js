/* global describe, it */
import {shallow} from 'enzyme'

import React from 'react'
import {expect} from 'chai'

import Account from '../pages/account.js'

const accountProp = {
  displayName: 'test-user'
}

describe('Account', () => {
  const account = shallow(<Account account={accountProp}/>)

  it('is DIVided', () => {
    expect(account.find('.account')).to.be.an('object')
  })

  it('is personalized', () => {
    expect(account.find('h1').text()).to.equal(`Hi ${accountProp.displayName}`)
  })
})
