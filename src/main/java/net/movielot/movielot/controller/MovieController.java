package net.movielot.movielot.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.movielot.movielot.domain.Emotion;
import net.movielot.movielot.enums.EmotionConstant;
import net.movielot.movielot.response.MovieDetailsResponse;
import net.movielot.movielot.response.SuccessResponse;
import net.movielot.movielot.response.tmdb.TMDBMovieListResponse;
import net.movielot.movielot.service.EmotionService;
import net.movielot.movielot.service.MovieService;
import net.movielot.movielot.util.ApiResponseUtil;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/movies")
@Slf4j
public class MovieController {

    private final MovieService movieService;
    private final EmotionService emotionService;

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

    @PostMapping("/{id}/emotions")
    public void expressEmotion(
            @PathVariable int id,
            @RequestParam EmotionConstant emotion
    ) {
        emotionService.expressEmotion(id, emotion);
        log.info("express emotion successfully");
    }

    @GetMapping("/{id}/emotions")
    public List<Emotion> expressEmotion(
            @PathVariable int id
    ) {
        return emotionService.getEmotionsFor(id);
    }
}
