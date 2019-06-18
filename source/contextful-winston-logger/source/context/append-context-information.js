// @flow
import {getNamespace} from 'continuation-local-storage';

import {LOGGING_CONTEXT, UUID_PARAMETER} from '../constants';

const appendContextInformation = (message) => {
  const loggingContext = getNamespace(LOGGING_CONTEXT);
  const uuid = loggingContext.get(UUID_PARAMETER)
  return `${uuid ? `${uuid} - ` : ''}${message}`;
};

export default appendContextInformation;
