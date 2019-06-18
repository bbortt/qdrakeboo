// @flow
import {createLogger, format, transports} from 'winston';

import loggerWrapper from './logger-wrapper';

const {combine, timestamp} = format;

import dotenv from 'dotenv';
dotenv.config();

const configureLogger = (loggingFormat, label = {}) => {
  const winstonLogger = createLogger({
    format: combine(
        label(label),
        timestamp(),
        loggingFormat
    ),
    transports: [new transports.Console()]
  });

  return loggerWrapper(winstonLogger);
};

export default configureLogger;
