package com.github.sanzhidev.moviereviewapi.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ConfigurationProperties(prefix = "tmdb")
public class TmdbProperties {

    private String apiKey;

    private String baseUrl;
}
