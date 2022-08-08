package net.movielot.movielot.service;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import net.movielot.movielot.domain.Movie;
import net.movielot.movielot.infrastructure.MovieRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;

    public Movie createDummy() {
        Movie dummy = Movie.builder()
            .id(UUID.randomUUID().toString())
            .title("test title!")
            .thumbnail("test thumbnail!")
            .build();
        return movieRepository.save(dummy).block();
    }

}
