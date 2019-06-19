import uuidv1 from 'uuid/v1';

import {createNamespace} from 'continuation-local-storage';

import {LOGGING_CONTEXT, UUID_PARAMETER} from '../constants';

const loggingContext = createNamespace(LOGGING_CONTEXT);

const contextCreatingMiddleware = (req, res, next) => {
  loggingContext.run(() => {
    loggingContext.set(UUID_PARAMETER, uuidv1());
    next();
  });
};

export default contextCreatingMiddleware;
