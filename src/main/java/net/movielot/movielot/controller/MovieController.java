package net.movielot.movielot.controller;

import lombok.RequiredArgsConstructor;
import net.movielot.movielot.service.MovieService;
import org.springframework.stereotype.Controller;

@RequiredArgsConstructor
@Controller
public class MovieController {

    private final MovieService movieService;

}
