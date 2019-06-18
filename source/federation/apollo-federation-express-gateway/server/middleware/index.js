const loggingContextMiddleware = require('./logging-context.middleware');
const inboundLoggingMiddleware = require('./inbound-logging.middleware');
const jwtMiddleware = require('./jwt.middleware');
const errorLoggingMiddleware = require('./error-logging.middleware');
const outboundLoggingMiddleware = require('./outbound-logging.middleware');

const middleware = [
  loggingContextMiddleware,
  inboundLoggingMiddleware,
  jwtMiddleware,
  errorLoggingMiddleware,
  outboundLoggingMiddleware
]

module.exports = middleware;
