const logger = require('../logging/logger');

const outboundLogging = (req, res, next) => {
  logger.info(`Outbound: ${JSON.stringify({"code": res.statusCode})}`);
  next();
};

module.exports = outboundLogging;
