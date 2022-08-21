package net.movielot.movielot.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.movielot.movielot.response.SuccessResponse;
import net.movielot.movielot.response.tmdb.TMDBMovieResponse;
import net.movielot.movielot.service.MovieService;
import net.movielot.movielot.util.ApiResponseUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/movies")
@Slf4j
public class MovieController {

    private final MovieService movieService;

    @GetMapping("/now")
    public SuccessResponse<TMDBMovieResponse> getNowPlayingMovies(
        @RequestParam(required = false, defaultValue = "1") int page
    ) {
        TMDBMovieResponse result = movieService.getNowPlayMovies(page);
        log.info(result.toString());
        return ApiResponseUtil.createSuccess(result);
    }

}
