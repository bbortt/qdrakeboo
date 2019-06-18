// @flow
import appendContextInformation from './append-context-information';

const loggingWrapper = (winstonLogger) => {
  return {
    log: (level, message) => {
      winstonLogger.log(level, appendContextInformation(message));
    },
    error: (message) => {
      winstonLogger.error(appendContextInformation(message));
    },
    warn: (message) => {
      winstonLogger.warn(appendContextInformation(message));
    },
    verbose: (message) => {
      winstonLogger.verbose(appendContextInformation(message));
    },
    info: (message) => {
      winstonLogger.info(appendContextInformation(message));
    },
    debug: (message) => {
      winstonLogger.debug(appendContextInformation(message));
    },
    silly: (message) => {
      winstonLogger.silly(appendContextInformation(message));
    }
  }
};

export default loggingWrapper;
