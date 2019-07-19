const express = require('express');
const session = require('express-session');

const next = require('next');

const configurePassport = require(
    './server/config/passport.config').configurePassport;
const passport = configurePassport();

const apiRouter = require('./server/router/api.router');
const authRouter = require('./server/router/auth.router');
const userRouter = require('./server/router/user.router');

const logger = require('./server/logging/logger');
const {bindContextfulMiddleware} = require('contextful-winston-logger');

const secured = require('./server/middleware/secured.middleware');

const dotenv = require('dotenv');
dotenv.config();

const dev = process.env.NODE_ENV !== 'production';
const app = next({dev});

const sessionConfig = {
  secret: process.env.COOKIE_SECRET || 'random-secret *laughs*',
  cookie: {},
  resave: false,
  saveUninitialized: true
};

if (!dev) {
  sessionConfig.cookie.secure = true;

  const redis = require('redis').createClient({
    host: process.env.REDIS_HOST || '127.0.0.1',
    port: process.env.REDIS_PORT || 6379
  });
  const RedisStore = require('connect-redis')(session);
  sessionConfig.store = new RedisStore({client: redis})
}

const applicationName = process.env.APP_NAME || 'Next.js Webapplication';
logger.info(`Starting ${applicationName}..`);

app.prepare().then(() => {
  const server = express();
  const port = process.env.PORT || 3000;
  const handle = app.getRequestHandler();

  server.use(session(sessionConfig));
  server.use(passport.initialize());
  server.use(passport.session());
  server.use(bindContextfulMiddleware(logger));

  server.use('/', apiRouter);
  server.use('/', authRouter);
  server.use('/', userRouter);

  const unsecuredRequest = (req, res) => {
    if (req.user) {
      return res.redirect('/home');
    }

    handle(req, res)
  };

  server.get('/', unsecuredRequest);
  server.get('/goodbye', unsecuredRequest);

  server.get('/_next/*', (req, res) => handle(req, res));
  server.get('/favicon.ico', (req, res) => handle(req, res));

  server.get('*', secured(), (req, res) => {
    const {_raw, _json, ...userProfile} = req.user;
    return app.render(req, res, req.path, {userInfo: userProfile})
  });

  server.listen({port: port}, (err) => {
    if (err) {
      throw err
    }

    logger.info(`🚀 Server ready at http://localhost:${port}`);
  })
});
