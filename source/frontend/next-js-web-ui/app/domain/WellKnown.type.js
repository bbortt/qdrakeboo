// @flow
export type WellKnownType = {
  auth0: {
    domain: string,
    clientId: string,
    callbackUrl: string,
  },
  api: {
    audience: string,
    url: string,
  },
  logoutRedirect: string,
}
