package com.github.sanzhidev.moviereviewapi.tmdb.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TmdbMovieDto {

    private Long id;

    private String title;

    private String overview;

    @JsonProperty("realease_date")
    private String releaseDate;

    @JsonProperty("vote_average")
    private Double voteAverage;
}
