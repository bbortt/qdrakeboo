// @flow
import Token from '../../domain/session/Token';

export const SET_TOKEN: string = 'Session: Set'

export type SetTokenAction = { type: string, token: Token }

export type SessionAction =
    SetTokenAction

export function setToken(token: Token): SetTokenAction {
  return {type: SET_TOKEN, token: token}
}
