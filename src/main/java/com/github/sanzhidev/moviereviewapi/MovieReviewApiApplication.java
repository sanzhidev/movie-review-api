package com.github.sanzhidev.moviereviewapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class MovieReviewApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(MovieReviewApiApplication.class, args);
    }

}
