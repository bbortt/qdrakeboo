/* global describe, it */
import { shallow } from 'enzyme'

import React from 'react'
import { expect } from 'chai'

import { ProfileClass } from '../../../../pages/app/account/profile'

const userInfo = {
  displayName: 'test-user',
}

describe('Profile', () => {
  const profile = shallow(<ProfileClass userInfo={userInfo} />)

  it('is DIVided', () => {
    expect(profile.find('.profile')).to.be.an('object')
  })

  it('is personalized', () => {
    expect(profile.find('h2').text()).to.equal(`Hi ${userInfo.displayName}`)
  })
})
