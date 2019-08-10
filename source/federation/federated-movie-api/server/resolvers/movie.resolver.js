const getAllMovies = () => {
  return [{name: 'hello world movie'}]
};

const movieResolver = {
  getAllMovies: getAllMovies
};

module.exports = movieResolver;
