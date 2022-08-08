package net.movielot.movielot.infrastructure;

import net.movielot.movielot.domain.Movie;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends ReactiveCrudRepository<Movie, String> {

}
