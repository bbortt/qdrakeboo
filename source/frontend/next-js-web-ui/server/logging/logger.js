const winston = require('winston');
const {format} = require('winston');

const dotenv = require('dotenv');
dotenv.config();

const {printf} = format;

const loggingFormat = printf(({level, message, label, timestamp}) => {
  return `${timestamp} [${label}] [${level.toUpperCase()}] ${message}`;
});

winston.level = process.env.LOG_LEVEL || 'info';
const applicationName = process.env.APP_NAME || 'Next.js Webapplication';

const {configureLogger} = require('contextful-winston-logger');
const logger = configureLogger(loggingFormat, {label: applicationName});

module.exports = logger;
