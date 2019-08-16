const configureErrorLoggingMiddleware = (logger) => {
  return (err, req, res, next) => {
    if (err) {
      logger.error(`Failed to fetch resource: ${JSON.stringify(
          {'responseCode': err.status, 'cause': `${err}!`})}`);
      res.status(err.status).json({message: err.message});
      return;
    }

    next()
  };
};

export default configureErrorLoggingMiddleware;
