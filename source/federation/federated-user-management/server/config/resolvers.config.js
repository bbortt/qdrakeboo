const userResolver = require('../resolvers/user.resolver');

const USER_ID_HEADER_NAME = 'qdrakeboo-user-id';

const resolvers = {
  Mutation: {
    updatePassword(_, args) {
      console.log('_ is   : ', _);
      console.log('args is: ', args)

      return userResolver.updatePassword(null, args.password, args.confirmation);
    }
  }
};

module.exports = resolvers;
