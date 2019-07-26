/* global describe, it */
import {shallow} from 'enzyme'

import React from 'react'
import {expect} from 'chai'

import Profile from '../../pages/account/profile'

const settingsProps = {
  displayName: 'test-user'
};

describe('Profile', () => {
  const profile = shallow(<Profile account={settingsProps}/>);

  it('is DIVided', () => {
    expect(profile.find('.profile')).to.be.an('object')
  });

  it('is personalized', () => {
    expect(profile.find('h1').text()).to.equal(
        `Hi ${settingsProps.displayName}`)
  })
});
