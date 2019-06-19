import contextCreatingMiddleware from './context-creating.middleware';
import configureInboundLoggingMiddleware
  from './configure-inbound-logging.middleware';
import configureErrorLoggingMiddleware
  from './configure-error-logging.middleware';
import configureOutboundLoggingMiddleware
  from './configure-outbound-logging.middleware';

const bindContextfulMiddleware = (logger, middleware = []) => {
  return [
    contextCreatingMiddleware,
    configureInboundLoggingMiddleware(logger),
    middleware,
    configureErrorLoggingMiddleware(logger),
    configureOutboundLoggingMiddleware(logger)
  ];
};

export default bindContextfulMiddleware;
