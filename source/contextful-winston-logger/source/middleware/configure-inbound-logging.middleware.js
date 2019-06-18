// @flow
const configureInboundMiddleware = (logger) => {
  return (req, res, next) => {
    logger.info(`Inbound: ${JSON.stringify({"url": req.url})}`);
    next();
  };
};

export default configureInboundMiddleware;
