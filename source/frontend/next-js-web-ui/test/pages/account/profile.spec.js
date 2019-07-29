/* global describe, it */
import {shallow} from 'enzyme'

import React from 'react'
import {expect} from 'chai'

import {Profile} from '../../../pages/account/profile'

const userInfo = {
  displayName: 'test-user'
};

describe('Profile', () => {
  const profile = shallow(<Profile userInfo={userInfo}/>);

  it('is DIVided', () => {
    expect(profile.find('.profile')).to.be.an('object')
  });

  it('is personalized', () => {
    expect(profile.find('h2').text()).to.equal(
        `Hi ${userInfo.displayName}`)
  })
});
