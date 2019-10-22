require('dotenv').config(process.env.DOTENV_FOLDER);

const {ApolloServer} = require('apollo-server-express');
const {buildFederatedSchema} = require('@apollo/federation');

const typeDefs = require('./server/config/type-defs.config');
const resolvers = require('./server/config/resolvers.config');

const express = require('express');
const actuator = require('express-actuator')

const logger = require('./server/logging/logger');
const {bindContextfulMiddleware} = require('contextful-winston-logger');

const applicationName = process.env.APP_NAME || 'Federated User Management';
logger.info(`Starting ${applicationName}..`);

const actuatorConfig = {
  basePath: '/actuator',
  infoGitMode: 'simple',
}

if (process.env.NODE_ENV !== 'production'){
  actuatorConfig.infoGitMode='full'
}

const server = new ApolloServer({
  schema: buildFederatedSchema([
    {
      typeDefs,
      resolvers
    }
  ]),
  context: ({ req }) => ({
    userId: req.headers['qdrakeboo-user-id']
  })
});

(async () => {
  const app = express();

  app.use(actuator(actuatorConfig))
  app.use(bindContextfulMiddleware(logger));

  server.applyMiddleware({app, path: '/'});

  const port = process.env.PORT || 4010;

  app.listen({port: port}, () => logger.info(
      `🚀 ${applicationName} ready at http://localhost:${port}${server.graphqlPath}`))
})();
