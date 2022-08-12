package net.movielot.movielot.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.movielot.movielot.configuration.TMDBClient;
import net.movielot.movielot.infrastructure.MovieRepository;
import net.movielot.movielot.response.tmdb.TMDBMovieResponse;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class MovieService {

    private final MovieRepository movieRepository;
    private final TMDBClient tmdbClient;

    public TMDBMovieResponse getNowPlayMovies(int page) {
        return tmdbClient.getLatestMovies(page);
    }
}
