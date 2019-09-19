const ManagementClient = require('auth0').ManagementClient;

const managementClient = new ManagementClient({
  domain: process.env.AUTH0_DOMAIN,
  clientId: process.env.AUTH0_CLIENT_ID,
  clientSecret: process.env.AUTH0_CLIENT_SECRET,
  scope: 'update:users update:users_app_metadata'
});

module.exports = managementClient;
