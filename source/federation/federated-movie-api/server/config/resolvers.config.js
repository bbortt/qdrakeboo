const movieResolver = require('../resolvers/movie.resolver');

const resolvers = {
  Query: {
    getAllMovies() {
      return movieResolver.getAllMovies();
    }
  }
};

module.exports = resolvers;
