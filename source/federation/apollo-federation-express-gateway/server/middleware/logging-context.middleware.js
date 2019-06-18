const logger = require('../logging/logger');
const uuidv1 = require('uuid/v1');

const createNamespace = require('continuation-local-storage').createNamespace;
const loggingContext = createNamespace('logging-context');

const inboundLogging = (req, res, next) => {
  loggingContext.run(() => {
    loggingContext.set('uuid', uuidv1());
    next();
  });
};

module.exports = inboundLogging;
