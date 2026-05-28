package com.github.sanzhidev.moviereviewapi.tmdb;

import com.github.sanzhidev.moviereviewapi.tmdb.client.TmdbClient;
import jakarta.persistence.NamedStoredProcedureQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TestTmdbController {

    private final TmdbClient tmdbClient;

    @GetMapping("/tmdb")
    public String test(@RequestParam String query){
        return tmdbClient.searchMovie(query);
    }
}
