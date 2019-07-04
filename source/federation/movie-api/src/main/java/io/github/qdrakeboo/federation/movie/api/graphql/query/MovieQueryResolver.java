package io.github.qdrakeboo.federation.movie.api.graphql.query;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import io.github.qdrakeboo.federation.movie.api.domain.Movie;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class MovieQueryResolver implements GraphQLQueryResolver {

  public Movie movie(UUID id) {
    return null;
  }

  public List<Movie> allMovies(int first, UUID after) {
    return null;
  }
}
