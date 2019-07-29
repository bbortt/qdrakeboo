/* global describe, it */
import {shallow} from 'enzyme'

import React from 'react'
import {expect} from 'chai'

import {Goodbye} from '../../pages/goodbye'

describe('Goodbye', () => {
  const goodbye = shallow(<Goodbye/>);

  it('is DIVided', () => {
    expect(goodbye.find('.goodbye')).to.be.an('object')
  });

  it('has correct application title', () => {
    expect(goodbye.find('h1').text()).to.equal('We hope to see you again soon..')
  });

  it('has sign in button', () => {
    expect(goodbye.find('button').text()).to.equal('Back to Qdrakeboo')
  })
});
