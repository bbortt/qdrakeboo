const updatePassword = require('../resolvers/update-password.resolver');

const USER_ID_HEADER_NAME = 'qdrakeboo-user-id';

const resolvers = {
  Mutation: {
    updatePassword(_, args) {
      return updatePassword(null, args.password, args.confirmation);
    }
  }
};

module.exports = resolvers;
