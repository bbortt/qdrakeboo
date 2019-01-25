import es6promise from 'es6-promise'

import {authenticationSaga} from './authentication.saga'

es6promise.polyfill()

export default function* () {
  yield authenticationSaga()
}
