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
        });

        providers.subscribe(response -> {
            MovieDetailsResponse.TMDBProvider tmdbProvider = new MovieDetailsResponse.TMDBProvider();
            BeanUtils.copyProperties(response, tmdbProvider);
            result.setProviders(tmdbProvider);
        });
        videos.subscribe(response -> {
            MovieDetailsResponse.TMDBVideo tmdbVideo = new MovieDetailsResponse.TMDBVideo();
            BeanUtils.copyProperties(response, tmdbVideo);
            result.setVideos(tmdbVideo);
        });

        details.block();
        providers.block();
        videos.block();

        return result;
    }
}
