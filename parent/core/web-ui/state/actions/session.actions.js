// @flow
export const SESSION_REQUEST: string = 'Session: Request'
export const SESSION_REQUEST_FAILED: string = 'Session: Failed'
export const SESSION_REQUEST_SUCCEED: string = 'Session: Succeed'

export type SessionRequestAction = { type: string, isServer: boolean }
export type SessionRequestFailedAction = { type: string }
export type SessionRequestSucceedAction = { type: string, authentication: any }

export type SessionAction =
    SessionRequestAction
    | SessionRequestFailedAction
    | SessionRequestSucceedAction

export function sessionRequest(isServer: boolean): SessionRequestAction {
  return {type: SESSION_REQUEST, isServer: isServer}
}

export function sessionRequestFailed(): SessionRequestFailedAction {
  return {type: SESSION_REQUEST_FAILED}
}

export function sessionRequestSucceed(authentication: any): SessionRequestSucceedAction {
  return {type: SESSION_REQUEST_SUCCEED, authentication: authentication}
}
