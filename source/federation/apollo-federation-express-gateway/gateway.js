const express = require('express');

const {ApolloServer} = require('apollo-server-express');
const {ApolloGateway} = require('@apollo/gateway');

const logger = require('./server/logging/logger');
const {bindContextfulMiddleware} = require('contextful-winston-logger');

const jwtAuthz = require('express-jwt-authz');

const dotenv = require('dotenv');
dotenv.config();

const federationClients = require('./federation-clients');
const gateway = new ApolloGateway(federationClients);

const applicationName = process.env.APP_NAME || 'Apollo Federation Gateway';
logger.info(`Starting ${applicationName}..`);

(async () => {
  const {schema, executor} = await gateway.load();

  const server = new ApolloServer({schema, executor});

  const app = express();
  app.use(bindContextfulMiddleware(logger, jwtAuthz(['graphql:query'])));

  server.applyMiddleware({app});

  const port = process.env.PORT || 4000;

  app.listen({port: port}, () => logger.info(
      `🚀 ${applicationName} ready at http://localhost:${port}${server.graphqlPath}`))
})();
