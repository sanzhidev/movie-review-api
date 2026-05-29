package com.github.sanzhidev.moviereviewapi.domain.review;

import com.github.sanzhidev.moviereviewapi.auth.dto.ReviewResponse;
import com.github.sanzhidev.moviereviewapi.domain.review.repository.ReviewRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("/{movieId}/reviews")
    public ResponseEntity<Review> addReview(
            @PathVariable Long movieId,
            @RequestParam Integer rating,
            @RequestParam(required = false) String content) {
                return ResponseEntity.ok(reviewService.addReview(movieId,rating ,content));
    }
    @GetMapping("/{movieId}/reviews")
    public ResponseEntity<List<Review>> getReviews(@PathVariable Long movieId) {
        return ResponseEntity.ok(reviewService.getReviewsByMovie(movieId));
    }

}
