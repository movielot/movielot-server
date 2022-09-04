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

        TMDBMovieDetailsResponse details = tmdbClient.getDetails(id);
        TMDBProvidersResponse providers = tmdbClient.getProviders(id);
        TMDBMovieVideosResponse videos = tmdbClient.getVideos(id);

        BeanUtils.copyProperties(details, result);

        List<MovieDetailsResponse.Genre> collect = details.getGenres().stream()
                .map(genre -> new MovieDetailsResponse.Genre(genre.getId(), genre.getName()))
                .collect(Collectors.toList());
        result.setGenres(collect);

        result.setProviders(providers);
        result.setVideos(videos);

        return result;
    }
}
