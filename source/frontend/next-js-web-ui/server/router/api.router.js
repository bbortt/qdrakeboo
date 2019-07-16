const express = require('express');
const secured = require('../middleware/secured.middleware');

const fetch = require('node-fetch');

const API_FORWARD_TO_HEADER_NAME = require(
    '../../shared/const').API_FORWARD_TO_HEADER_NAME;

const logger = require('../logging/logger');

const router = express.Router();

const performAPIRequest = async (req, res, httpMethod) => {
  const {accessToken} = req.session.authentication;

  const requestUrl = req.get(API_FORWARD_TO_HEADER_NAME);
  if (!requestUrl) {
    logger.warn(
        `Received API request without header '${API_FORWARD_TO_HEADER_NAME}'`);

    return res.status(400)
    .end(JSON.stringify(
        {message: `missing header '${API_FORWARD_TO_HEADER_NAME}'`}))
  }

  try {
    logger.debug(`Starting request to API '${requestUrl}'`);

    const response = await fetch(`${requestUrl}`,
        {
          method: httpMethod,
          headers: {
            'Authorization': `Bearer ${accessToken}`
          }
        });

    logger.debug(
        `Response from API: ${JSON.stringify({status: response.status})}`);

    if (response.status && response.status === 401) {
      req.session.returnTo = req.originalUrl;
      return res.redirect('/login');
    }

    res.status(response.status);
    res.setHeader('content-type', response.headers.get('content-type'));
    res.end(JSON.stringify(await response.json()))
  } catch (error) {
    logger.error(`Error occurred when fetching ${requestUrl}: ${error}`);

    res.status(500).end(JSON.stringify({message: error.message}));
  }
};

router.get('/api', secured(),
    async (req, res, next) => await performAPIRequest(req, res, 'GET'));

router.post('/api', secured(),
    async (req, res, next) => await performAPIRequest(req, res, 'POST'));

router.put('/api', secured(),
    async (req, res, next) => await performAPIRequest(req, res, 'PUT'));

router.patch('/api', secured(),
    async (req, res, next) => await performAPIRequest(req, res, 'PATCH'));

module.exports = router;
