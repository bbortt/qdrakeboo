const movieResolver = require('../resolvers/movie.resolver');

const USER_ID_HEADER_NAME = 'qdrakeboo-user-id';

const resolvers = {
  Query: {
    getAllMovies() {
      return movieResolver.getAllMovies();
    }
  }
};

module.exports = resolvers;
