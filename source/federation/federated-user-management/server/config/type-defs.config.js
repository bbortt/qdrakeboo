const {gql} = require('apollo-server-express');

const typeDefs = gql`
  extend type Mutation {
    updatePassword(updatePasswordInput: UpdatePasswordInput!): Boolean
  }

  input UpdatePasswordInput {
    password: String!
    confirmation: String!
  }
`;

module.exports = typeDefs;
