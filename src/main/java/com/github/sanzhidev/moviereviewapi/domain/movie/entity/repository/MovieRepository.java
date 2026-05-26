package com.github.sanzhidev.moviereviewapi.domain.movie.entity.repository;

import com.github.sanzhidev.moviereviewapi.domain.movie.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    Optional<Movie> findByTmdbId(Integer tmdbId);
    boolean existsByTmdbId(Integer tmdbId);
}
