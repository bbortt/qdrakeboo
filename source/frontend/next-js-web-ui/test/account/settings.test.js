/* global describe, it */
import {shallow} from 'enzyme'

import React from 'react'
import {expect} from 'chai'

import Settings from '../../pages/account/settings'

const settingsProps = {
  displayName: 'test-user'
};

describe('Settings', () => {
  const settings = shallow(<Settings account={settingsProps}/>);

  it('is DIVided', () => {
    expect(settings.find('.settings')).to.be.an('object')
  });

  it('is personalized', () => {
    expect(settings.find('h1').text()).to.equal(`Hi ${settingsProps.displayName}`)
  })
});
