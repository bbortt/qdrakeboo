import {createLogger, format, transports} from 'winston';

import loggerWrapper from './logger-wrapper';

const {combine, label, timestamp} = format;

const configureLogger = (loggingFormat, labelTarget = {}) => {
  const winstonLogger = createLogger({
    format: combine(
        label(labelTarget),
        timestamp(),
        loggingFormat
    ),
    transports: [new transports.Console()]
  });

  return loggerWrapper(winstonLogger);
};

export default configureLogger;
