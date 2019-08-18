const dotenv = require('dotenv');
dotenv.config();

const {ApolloServer} = require('apollo-server-express');
const {buildFederatedSchema} = require('@apollo/federation');

const typeDefs = require('./server/config/type-defs.config');
const resolvers = require('./server/config/resolvers.config');

const express = require('express');

const logger = require('./server/logging/logger');
const {bindContextfulMiddleware} = require('contextful-winston-logger');

const applicationName = process.env.APP_NAME || 'Federated Movie API';
logger.info(`Starting ${applicationName}..`);

const server = new ApolloServer({
  schema: buildFederatedSchema([
    {
      typeDefs,
      resolvers
    }
  ])
});

(async () => {
  const app = express();
  server.applyMiddleware({app})

  app.use(bindContextfulMiddleware(logger));

  const port = process.env.PORT || 4011;

  app.listen({port: port}, () => logger.info(
      `🚀 ${applicationName} ready at http://localhost:${port}`))
})();
