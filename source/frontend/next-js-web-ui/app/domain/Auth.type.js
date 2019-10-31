// @flow
export type AuthType = {
  user: {
    email: string,
    email_verified: boolean,
    name: string,
    nickname: string,
    picture: string,
    sub: string,
    updated_at: string,
  },
  error: string,
  isAuthenticated: boolean,
  isLoading: boolean,
  login: () => void,
  logout: () => void,
}
