/* global describe, it */
import { shallow } from 'enzyme'

import React from 'react'
import { expect } from 'chai'

import { ResetPassword } from '../../../pages/account/reset-password'

describe('ResetPassword', () => {
  const resetPassword = shallow(<ResetPassword />)

  it('is DIVided', () => {
    expect(resetPassword.find('.reset-password')).to.be.an('object')
  })

  it('states title', () => {
    expect(resetPassword.find('h2').text()).to.equal('Reset Password')
  })
})
