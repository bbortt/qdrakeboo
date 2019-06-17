const express = require('express');
const next = require('next');

const dev = process.env.NODE_ENV !== 'production';
const app = next({dev});

const handle = app.getRequestHandler();

// TODO: Give statement (logging) that server is starting up

app.prepare().then(() => {
  const server = express();
  const port = process.env.PORT || 3000;

  server.get('*', (req, res) => handle(req, res));

  server.listen({port: port}, (err) => {
    if (err) {
      throw err
    }

    // TODO: User Logger
    console.log(
        `🚀 Server ready at http://localhost:${port}${server.graphqlPath}`)
  })
});
