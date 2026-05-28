package com.github.sanzhidev.moviereviewapi.tmdb.client;

import com.github.sanzhidev.moviereviewapi.config.TmdbProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
@RequiredArgsConstructor
public class TmdbClient {

    private final RestClient restClient;

    private final TmdbProperties properties;

    public String searchMovie(String query){

        return restClient.get()
                .uri(uriBuilder  -> uriBuilder
                        .path("/search/movie")
                        .queryParam("api_key",properties.getApiKey())
                        .queryParam("query",query)
                        .build())
                .retrieve()
                .body(String.class);
    }
}
