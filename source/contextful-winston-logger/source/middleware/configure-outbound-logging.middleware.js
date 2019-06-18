// @flow
const configureOutboundLoggingMiddleware = (logger) => {
  return (req, res, next) => {
    logger.info(`Outbound: ${JSON.stringify({"code": res.statusCode})}`);
    next();
  };
};

export default configureOutboundLoggingMiddleware;
