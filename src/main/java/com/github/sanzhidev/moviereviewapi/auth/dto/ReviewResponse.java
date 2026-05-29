package com.github.sanzhidev.moviereviewapi.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ReviewResponse {
    private Long id;
    private UserResponse user;
    private Long movieId;
    private String content;
    private Integer rating;
    private LocalDateTime createdAt;
}
