// @flow
import type { ReduxState } from '../state/reducer'

export type Context = {
  isServer: boolean,
  pathname: string,
  query: any,
  req?: any,
  res?: any,
  err?: any,
  store: {
    dispatch: (action: any) => void,
    getState: () => ReduxState,
  },
}
