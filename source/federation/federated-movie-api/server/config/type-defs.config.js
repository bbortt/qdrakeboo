const {gql} = require('apollo-server-express');

const typeDefs = gql`
  extend type Query {
    getAllMovies: [Movie]!
  }

  type Movie {
    name: String!
  }
`;

module.exports = typeDefs;
