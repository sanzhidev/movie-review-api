package com.github.sanzhidev.moviereviewapi.domain.movie.controller;

import com.github.sanzhidev.moviereviewapi.domain.movie.entity.Movie;
import com.github.sanzhidev.moviereviewapi.domain.movie.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/movies")
@RequiredArgsConstructor

public class MovieController {

    private final MovieService movieService;

    @PostMapping("/import")
    public Movie  importMovie(@RequestParam String query) {
        return movieService.importMovie(query);
    }
}
