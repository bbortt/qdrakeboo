const express = require('express');
const secured = require('../middleware/secured.middleware');

const fetch = require('node-fetch');
const config = require('../../next.config');

const router = express.Router();

router.get('/api', secured(), async (req, res, next) => {
  const {accessToken} = req.session.authentication;

  // try {
  const response = await fetch(`${config.publicRuntimeConfig.apiUrl}/principal`,
      {
        method: 'GET',
        headers: {
          'Authorization': `Bearer ${accessToken}`
        }
      });
  //
  //   if (response.status && response.status === 401) {
  //     return res.redirect('/session/renew')
  //   }
  //
  res.status(response.status)
  res.setHeader('content-type', response.headers.get('content-type'))
  res.end(JSON.stringify(await response.json()))
  // } catch (error) {
  //   // TODO: Use a logger
  //   console.log(`error fetching ${apiUrl}: ${error}`)
  //
  //   res.redirect('/error')
  // }
});

module.exports = router;
