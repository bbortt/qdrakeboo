// @flow
export type Context = {
  isServer: boolean,
  pathname: string,
  query: any,
  req?: any,
  res?: any,
  err?: any
}
