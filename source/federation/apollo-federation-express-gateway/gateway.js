const express = require('express');

const {ApolloServer} = require('apollo-server-express');
const {ApolloGateway} = require('@apollo/gateway');

const jwt = require('express-jwt');
const jwtAuthz = require('express-jwt-authz');
const jwksRsa = require('jwks-rsa');

const dotenv = require('dotenv');
dotenv.config();

const federationClients = require('./federation-clients');
const gateway = new ApolloGateway(federationClients);

const checkJwt = jwt({
  secret: jwksRsa.expressJwtSecret({
    cache: true,
    rateLimit: true,
    jwksRequestsPerMinute: 5,
    jwksUri: process.env.JWKS_URI
  }),
  audience: process.env.AUDIENCE,
  issuer: process.env.ISSUER,
  algorithms: ['RS256']
});

const globalErrorHandler = (err, req, res, next) => {
  // TODO: User Logger
  console.error(err.stack);
  return res.status(err.status).json({message: err.message});
};

// TODO: Give statement (logging) that server is starting up

(async () => {
  const {schema, executor} = await gateway.load();

  const server = new ApolloServer({schema, executor})

  const app = express();
  app.use(checkJwt);
  app.use(globalErrorHandler);

  server.applyMiddleware({app})

  const port = process.env.PORT || 4000;

  app.listen({port: port}, () =>
      // TODO: User Logger
      console.log(
          `🚀 Server ready at http://localhost:${port}${server.graphqlPath}`)
  )
})();
