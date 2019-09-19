// @flow
export const RESET_PASSWORD: string = 'User-Management: Reset password'
export const RESET_PASSWORD_SUCCEED: string =
  'User-Management: Reset password succeed'

export type ResetPasswordAction = {
  type: string,
  password: string,
  confirmation: string,
}

export type ResetPasswordSucceedAction = {
  type: string,
}

export type UserManagementAction =
  | ResetPasswordAction
  | ResetPasswordSucceedAction

export const resetPassword = (password: string, confirmation: string) => {
  return { type: RESET_PASSWORD, password, confirmation }
}

export const resetPasswordSucceed = () => {
  return { type: RESET_PASSWORD_SUCCEED }
}
