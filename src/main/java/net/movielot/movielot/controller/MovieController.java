package net.movielot.movielot.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.movielot.movielot.response.MovieDetailsResponse;
import net.movielot.movielot.response.SuccessResponse;
import net.movielot.movielot.response.tmdb.TMDBMovieListResponse;
import net.movielot.movielot.service.MovieService;
import net.movielot.movielot.util.ApiResponseUtil;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/movies")
@Slf4j
public class MovieController {

    private final MovieService movieService;

    @GetMapping("/now")
    public SuccessResponse<TMDBMovieListResponse> getNowPlayingMovies(
        @RequestParam(required = false, defaultValue = "1") int page
    ) {
        TMDBMovieListResponse result = movieService.getNowPlayMovies(page);
        log.info(result.toString());
        return ApiResponseUtil.createSuccess(result);
    }

    @GetMapping("/{id}")
    public SuccessResponse<MovieDetailsResponse> getMovieDetail(
            @PathVariable String id
    ) {
        MovieDetailsResponse result = movieService.getDetails(id);
        log.info(result.toString());
        return ApiResponseUtil.createSuccess(result);
    }
}
