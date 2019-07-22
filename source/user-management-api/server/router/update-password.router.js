const express = require('express');

const managementClient = require('../config/managementClient.config');

const logger = require('../logging/logger');

const router = express.Router();

const USER_ID_HEADER_NAME = 'qdrakeboo-user-id';

router.post('/update-password', (req, res) => {
  const userId = req.get(USER_ID_HEADER_NAME);
  if (!userId) {
    return res.status(400).end(
        JSON.stringify({message: `missing header '${USER_ID_HEADER_NAME}'`}))
  }

  const feedback = validatePassword(req.body.password, req.body.confirmation);
  if (feedback) {
    return res.status(500).end(JSON.stringify({message: feedback}));
  }

  // managementClient.changePassword()
});

const validatePassword = (password, confirmation) => {

};

module.exports = router;
