const express = require('express');

const managementClient = require('../config/managementClient.config');

const logger = require('../logging/logger');

const router = express.Router();

const USER_ID_HEADER_NAME = 'qdrakeboo-user-id';

router.post('/update-password', async (req, res) => {
  const userId = req.get(USER_ID_HEADER_NAME);
  if (!userId) {
    return res.status(400).end(
        JSON.stringify({message: `missing header '${USER_ID_HEADER_NAME}'`}));
  }

  const {body} = res;

  if (!body) {
    return res.status(400).end(JSON.stringify(
        {message: 'Expected content: {password, confirmation}!'}));
  }

  const password = body.password;

  const feedback = validatePassword(password, body.confirmation);
  if (feedback) {
    return res.status(500).end(JSON.stringify({message: feedback}));
  }

  var params = {id: userId};
  return await managementClient.changePassword(params, {password: password},
      (error, user) => {
        if (error) {
          return res.status(500).end(
              JSON.stringify({message: 'Internal server error.'}));
        }

        return user;
      })
});

const validatePassword = (password, confirmation) => {
  if (!password) {
    return 'No password provided!'
  }

  if (!confirmation) {
    return 'Please confirm your new password!'
  }

  if (password !== confirmation) {
    return 'Password and confirmation do not match!'
  }

  return
};

module.exports = router;
