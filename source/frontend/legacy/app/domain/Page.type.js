// @flow
import { Component } from 'react'

import type { Context } from './Context.type'

export type Page<Props, State = {}> = Component<Props, State> & {
  getInitialProps: ({ ctx: Context }) => Promise<any>,
  renderPage(callback: Function): void,
}
