const ClientOAuth2 = require('client-oauth2')

module.exports = new ClientOAuth2({
  clientId: '3b732778-ca25-4d86-925a-37477b1b634d',
  clientSecret: '796f082b-90a0-45af-b4c1-3976b6f92c92',
  accessTokenUri: 'http://localhost:8088/auth/oauth/access_token',
  authorizationUri: 'http://localhost:8088/auth/oauth/authorize',
  redirectUri: 'http://localhost:3000',
  scopes: ['read']
})
