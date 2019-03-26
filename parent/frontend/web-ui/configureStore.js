import {applyMiddleware, createStore} from 'redux'
import createSagaMiddleware from 'redux-saga'

import {crashReportingMiddleware, loggingMiddleware} from './app/state/middleware'

import rootReducer, {initialState} from './app/state/reducer'
import rootSaga from './app/state/saga'

const bindMiddleware = middleware => {
  if (process.env.NODE_ENV !== 'production') {
    const {composeWithDevTools} = require('redux-devtools-extension')
    return composeWithDevTools(
      applyMiddleware(crashReportingMiddleware, loggingMiddleware,
        ...middleware))
  }
  return applyMiddleware(...middleware)
}

export default (initialState = initialState) => {
  const sagaMiddleware = createSagaMiddleware()

  const store = createStore(
    rootReducer,
    initialState,
    bindMiddleware([sagaMiddleware])
  )

  store.sagaTask = sagaMiddleware.run(rootSaga)

  return store
}
