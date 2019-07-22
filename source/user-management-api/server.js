const express = require('express');
const bodyParser = require('body-parser');

const updatePasswordRouter = require('./server/router/update-password.router');

const logger = require('./server/logging/logger');
const {bindContextfulMiddleware} = require('contextful-winston-logger');

const dotenv = require('dotenv');
dotenv.config();

const applicationName = process.env.APP_NAME || 'User Management Service';
logger.info(`Starting ${applicationName}..`);

(async () => {
  const app = express();
  app.use(bindContextfulMiddleware(logger, [bodyParser.json()]));

  app.use('/', updatePasswordRouter);

  const port = process.env.PORT || 5000;

  app.listen({port: port}, () => logger.info(
      `🚀 ${applicationName} ready at http://localhost:${port}`))
})();
