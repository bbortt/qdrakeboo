const {UserInputError} = require('apollo-server')

const managementClient = require('../config/management-client.config');

const logger = require('../logging/logger');

const updatePassword = async (userId, password, confirmation) => {
  if (!userId) {
    throw new UserInputError('Cannot reset password without UserId!')
  }

  const feedback = validatePassword(password, confirmation);
  if (feedback) {
    throw new UserInputError(feedback)
  }

   await managementClient.changePassword(
      {id: userId},
      {password: password},
      (error, user) => {
        if (error) {
          return res.status(500).end(
              JSON.stringify({message: 'Internal server error.'}));
        }

        return user;
      })
};

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
};

module.exports = {
  updatePassword: updatePassword
};
