// @flow
import { Store, Unsubscribe } from 'redux'
import type { ReduxState } from '../../state/reducer'

export default (
  store: Store<ReduxState>,
  select: (state: ReduxState) => any,
  onChange: (state: any) => void
): Unsubscribe => {
  let currentState

  function handleChange() {
    const nextState = select(store.getState())
    if (nextState !== currentState) {
      currentState = nextState
      onChange(currentState)
    }
  }

  return store.subscribe(handleChange)
}
