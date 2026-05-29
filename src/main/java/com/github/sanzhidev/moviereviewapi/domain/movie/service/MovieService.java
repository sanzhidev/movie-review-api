package com.github.sanzhidev.moviereviewapi.domain.movie.service;

import com.github.sanzhidev.moviereviewapi.domain.movie.entity.Movie;
import com.github.sanzhidev.moviereviewapi.domain.movie.entity.repository.MovieRepository;
import com.github.sanzhidev.moviereviewapi.tmdb.client.TmdbClient;
import com.github.sanzhidev.moviereviewapi.tmdb.dto.TmdbMovieDto;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository  movieRepository;
    private final TmdbClient tmdbClient;


    @Cacheable("movies")
    public List<Movie> getAllMovies(){
        return movieRepository.findAll();
    }

    @Cacheable(value = "movies",key = "#id")
    public Movie getMovieById(Long id) {
        return movieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Movie not found"));
    }


    @CacheEvict(value = "movies", allEntries = true)
    public Movie importMovie(String query){

        TmdbMovieDto dto = tmdbClient
                .searchMovie(query)
                .getResults()
                .getFirst();

        Movie movie = new Movie();

        movie.setTitle(dto.getTitle());
        movie.setOverview(dto.getOverview());

        return movieRepository.save(movie);
    }
}
