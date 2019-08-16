// @flow
export const RESET_PASSWORD: string = 'User-Management: Reset password'

export type ResetPasswordAction = {
  type: string,
  password: string,
  confirmation: string,
}

export type UserManagementAction = ResetPasswordAction

export const resetPassword = (password: string, confirmation: string) => {
  return { type: RESET_PASSWORD, password, confirmation }
}
