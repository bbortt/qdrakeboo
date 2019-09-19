const {gql} = require('apollo-server-express');

const typeDefs = gql`
  extend type Mutation {
    updatePassword(
      password: String!
      confirmation: String!
    ): Boolean
  }
`;

module.exports = typeDefs;
