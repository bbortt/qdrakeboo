const express = require('express');
const secured = require('../middleware/secured.middleware');
const router = express.Router();

router.get('/user', secured(), (req, res, next) => {
  const {...userProfile} = req.user;

  res.setHeader('Content-Type', 'application/json');
  res.end(JSON.stringify(userProfile));
});

module.exports = router;
