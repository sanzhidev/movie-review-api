package com.github.sanzhidev.moviereviewapi.domain.review.repository;

import com.github.sanzhidev.moviereviewapi.domain.review.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByMovieId(Long movieId);
    boolean existsByUserIdAndMovieId(Long userId, Long movieId);
}
