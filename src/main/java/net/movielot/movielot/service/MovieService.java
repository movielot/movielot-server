package net.movielot.movielot.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.movielot.movielot.configuration.TMDBClient;
import net.movielot.movielot.infrastructure.MovieRepository;
import net.movielot.movielot.response.MovieDetailsResponse;
import net.movielot.movielot.response.tmdb.TMDBMovieDetailsResponse;
import net.movielot.movielot.response.tmdb.TMDBMovieListResponse;
import net.movielot.movielot.response.tmdb.TMDBMovieVideosResponse;
import net.movielot.movielot.response.tmdb.TMDBProvidersResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class MovieService {

    private final MovieRepository movieRepository;
    private final TMDBClient tmdbClient;

    public TMDBMovieListResponse getNowPlayMovies(int page) {
        return tmdbClient.getLatestMovies(page);
    }

    public MovieDetailsResponse getDetails(String id) {
        MovieDetailsResponse result = new MovieDetailsResponse();

        Mono<TMDBMovieDetailsResponse> details = tmdbClient.getDetails(id);
        Mono<TMDBProvidersResponse> providers = tmdbClient.getProviders(id);
        Mono<TMDBMovieVideosResponse> videos = tmdbClient.getVideos(id);

        details.subscribe(response -> {
            BeanUtils.copyProperties(response, result);

            List<MovieDetailsResponse.Genre> collect = response.getGenres().stream()
                    .map(genre -> new MovieDetailsResponse.Genre(genre.getId(), genre.getName()))
                    .collect(Collectors.toList());
            result.setGenres(collect);
        });

        providers.subscribe(result::setProviders);
        videos.subscribe(result::setVideos);

        details.block();
        providers.block();
        videos.block();

        return result;
    }
}
