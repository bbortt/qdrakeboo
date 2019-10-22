require('dotenv').config(process.env.DOTENV_FOLDER);

const express = require('express');
const actuator = require('express-actuator')

const {ApolloServer} = require('apollo-server-express');
const {ApolloGateway} = require('@apollo/gateway');

const logger = require('./server/logging/logger');
const {bindContextfulMiddleware} = require('contextful-winston-logger');
const userIdAppendingMiddleware = require(
    './server/middleware/user-id-appending.middleware');

const fs = require('fs');
const federationClients = JSON.parse(
    fs.readFileSync('./federation-clients.json'));
const gateway = new ApolloGateway(federationClients);

const applicationName = process.env.APP_NAME || 'Apollo Federation Gateway';
logger.info(`Starting ${applicationName}..`);

const actuatorConfig = {
  basePath: '/actuator',
  infoGitMode: 'simple',
}

if (process.env.NODE_ENV !== 'production'){
  actuatorConfig.infoGitMode='full'
}

(async () => {
  const {schema, executor} = await gateway.load();

  const server = new ApolloServer({schema, executor});

  const app = express();
  app.use(actuator(actuatorConfig))
  app.use(bindContextfulMiddleware(logger, [userIdAppendingMiddleware]));

  server.applyMiddleware({app, path: '/'});

  const port = process.env.PORT || 4000;

  app.listen({port: port}, () => logger.info(
      `🚀 ${applicationName} ready at http://localhost:${port}${server.graphqlPath}`))
})();
