const winston = require('winston');
const {createLogger, format, transports} = require('winston');

const getNamespace = require('continuation-local-storage').getNamespace;

const dotenv = require('dotenv');
dotenv.config();

const {combine, timestamp, label, printf} = format;

const loggingFormat = printf(({level, message, label, timestamp}) => {
  return `${timestamp} [${label}] [${level.toUpperCase()}] ${message}`;
});

winston.level = process.env.LOG_LEVEL || 'info';
const applicationName = process.env.APP_NAME || 'Apollo Federation Gateway';

const winstonLogger = createLogger({
  format: combine(
      label({label: applicationName}),
      timestamp(),
      loggingFormat
  ),
  transports: [new transports.Console()]
});

const formatMessage = (message) => {
  const loggingContext = getNamespace('logging-context');

  const uuid = loggingContext.get('uuid')

  return `${uuid ? `${uuid} - ` : ''}${message}`;
}

const logger = {
  log: (level, message) => {
    winstonLogger.log(level, formatMessage(message));
  },
  error: (message) => {
    winstonLogger.error(formatMessage(message));
  },
  warn: (message) => {
    winstonLogger.warn(formatMessage(message));
  },
  verbose: (message) => {
    winstonLogger.verbose(formatMessage(message));
  },
  info: (message) => {
    winstonLogger.info(formatMessage(message));
  },
  debug: (message) => {
    winstonLogger.debug(formatMessage(message));
  },
  silly: (message) => {
    winstonLogger.silly(formatMessage(message));
  }
};

module.exports = logger;
