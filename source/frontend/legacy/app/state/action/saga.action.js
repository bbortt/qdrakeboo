// @flow
export const SAGA_STARTED: string = 'Saga: Started'

export type SagaStartedAction = {
  type: string,
}

export type SagaAction = SagaStartedAction

export const sagaStarted = (): SagaStartedAction => {
  return { type: SAGA_STARTED }
}
