package com.github.sanzhidev.moviereviewapi.tmdb.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TmdbMovieResponse {

    private List<TmdbMovieDto> results;
}
