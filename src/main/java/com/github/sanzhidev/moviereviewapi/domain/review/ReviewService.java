package com.github.sanzhidev.moviereviewapi.domain.review;


import com.github.sanzhidev.moviereviewapi.domain.movie.entity.Movie;
import com.github.sanzhidev.moviereviewapi.domain.movie.entity.repository.MovieRepository;
import com.github.sanzhidev.moviereviewapi.domain.review.repository.ReviewRepository;
import com.github.sanzhidev.moviereviewapi.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final MovieRepository movieRepository;
    private final ReviewRepository reviewRepository;

    public Review addReview(Long movieId, Integer rating,String content){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Movie movie = movieRepository.findById(movieId).orElseThrow(() -> new RuntimeException("Movie not found"));
        Review review = new Review();
        review.setUser(user);
        review.setMovie(movie);
        review.setRating(rating);
        review.setContent(content);
        review.setCreatedAt(LocalDateTime.now());

        return reviewRepository.save(review);
        }

        public List<Review> getReviewsByMovie(Long movieId){
        return reviewRepository.findByMovieId(movieId);
    }
}
