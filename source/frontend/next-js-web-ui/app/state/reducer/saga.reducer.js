// @flow
import { SAGA_STARTED } from '../action'
import type { SagaAction } from '../action'

export type SagaState = {
  ran: boolean,
}

export const initialSagaState: SagaState = {
  ran: false,
}

export default (
  state: SagaState = initialSagaState,
  action: SagaAction
): SagaState => {
  switch (action.type) {
    case SAGA_STARTED:
      return {
        ...state,
        ran: true,
      }
    default:
      return state
  }
}
