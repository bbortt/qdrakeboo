// @flow
import {Component} from 'react'

import type {Context} from './Context.flow'

export type Page<Props, State = {}> = Component<Props, State> & {
  getInitialProps: ({ ...Context }) => Promise<any>;
  renderPage(cb: Function): void;
}
