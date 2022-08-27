package net.movielot.movielot.configuration;

import lombok.extern.slf4j.Slf4j;
import net.movielot.movielot.response.imdb.IMDBRatingResponse;
import net.movielot.movielot.response.tmdb.TMDBMovieDetailsResponse;
import net.movielot.movielot.response.tmdb.TMDBMovieListResponse;
import net.movielot.movielot.response.tmdb.TMDBMovieVideosResponse;
import net.movielot.movielot.response.tmdb.TMDBProvidersResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Component
@Slf4j
public class IMDBClient {

    @Value("${client.imdb.apiKey}")
    private String apiKey;
    @Value("${client.imdb.host}")
    private String host;
    private final RestTemplate restTemplate = new RestTemplate();

    private HttpHeaders getHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }

    public IMDBRatingResponse getRating(String id) {
        HttpEntity<Void> httpEntity = new HttpEntity<>(getHttpHeaders());
        UriComponents uriComponents = UriComponentsBuilder
                .fromHttpUrl(host + "/API/Ratings/" + apiKey + "/" + id)
                .build();

        return restTemplate.exchange(uriComponents.toString(), HttpMethod.GET, httpEntity, IMDBRatingResponse.class).getBody();
    }
}
