const logger = require('../logging/logger');

const inboundLogging = (req, res, next) => {
  logger.info(`Inbound: ${JSON.stringify({"url": req.url})}`);
  next();
};

module.exports = inboundLogging;
