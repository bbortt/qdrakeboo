import {applyMiddleware, createStore} from 'redux'
import createSagaMiddleware from 'redux-saga'

import {crashReportingMiddleware, loggingMiddleware} from './state/middleware'

import rootReducer from './state/reducer'
import rootSaga from './state/saga'

const sagaMiddleware = createSagaMiddleware()

const bindMiddleware = (middleware) => {
  if (process.env.NODE_ENV !== 'production') {
    const {composeWithDevTools} = require('redux-devtools-extension')
    return composeWithDevTools(applyMiddleware(loggingMiddleware, crashReportingMiddleware, ...middleware))
  }

  return applyMiddleware(...middleware)
}

const store = createStore(
  rootReducer,
  bindMiddleware([sagaMiddleware])
)

store.runSagaTask = () => {
  store.sagaTask = sagaMiddleware.run(rootSaga)
}

store.runSagaTask()

export default function getStore() {
  return store
}
