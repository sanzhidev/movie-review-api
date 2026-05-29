package com.github.sanzhidev.moviereviewapi.domain.movie.controller;

import com.github.sanzhidev.moviereviewapi.domain.movie.entity.Movie;
import com.github.sanzhidev.moviereviewapi.domain.movie.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
@RequiredArgsConstructor

public class MovieController {

    private final MovieService movieService;

    @GetMapping
    public ResponseEntity<List<Movie>> getAllMovies() {
        return ResponseEntity.ok(movieService.getAllMovies());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable Long id) {
        return ResponseEntity.ok(movieService.getMovieById(id));
    }

    @PostMapping("/import")
    public Movie  importMovie(@RequestParam String query) {
        return movieService.importMovie(query);
    }
}
