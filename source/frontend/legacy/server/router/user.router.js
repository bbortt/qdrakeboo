const express = require('express')

const secured = require('../middleware/secured.middleware')

const router = express.Router()

router.get('/user-info', secured(), (req, res) => {
  const { _raw, _json, ...userProfile } = req.user

  res.setHeader('Content-Type', 'application/json')
  res.end(JSON.stringify(userProfile))
})

module.exports = router
