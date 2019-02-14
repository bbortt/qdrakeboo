import {applyMiddleware, createStore} from 'redux'
import createSagaMiddleware from 'redux-saga'

import {crashReportingMiddleware, loggingMiddleware} from './state/middleware'

import rootReducer, {initialState} from './state/reducer'
import rootSaga from './state/saga'

const bindMiddleware = middleware => {
  if (process.env.NODE_ENV !== 'production') {
    const {composeWithDevTools} = require('redux-devtools-extension')
    return composeWithDevTools(
        applyMiddleware(crashReportingMiddleware, loggingMiddleware,
            ...middleware))
  }
  return applyMiddleware(...middleware)
}

const sagaMiddleware = createSagaMiddleware()

export default (initialState = initialState) => {
  const store = createStore(
      rootReducer,
      initialState,
      bindMiddleware([sagaMiddleware])
  )

  store.sagaTask = sagaMiddleware.run(rootSaga)

  return store
}
