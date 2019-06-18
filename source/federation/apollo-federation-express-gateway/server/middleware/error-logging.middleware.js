const logger = require('../logging/logger');

const errorLoggingMiddleware = (err, req, res, next) => {
  if (err) {
    logger.error(`Failed to fetch resource: ${JSON.stringify(
        {"responseCode": err.status, "cause": `${err}!`})}`);
    res.status(err.status).json({message: err.message});
    return;
  }

  next()
};

module.exports = errorLoggingMiddleware;
