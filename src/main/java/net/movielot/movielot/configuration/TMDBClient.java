package net.movielot.movielot.configuration;

import lombok.extern.slf4j.Slf4j;
import net.movielot.movielot.response.tmdb.TMDBMovieResponse;
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
public class TMDBClient {

    @Value("${client.tmdb.apiKey}")
    private String apiKey;
    @Value("${client.tmdb.host}")
    private String host;
    private final RestTemplate restTemplate = new RestTemplate();

    public TMDBMovieResponse getLatestMovies(int page) {
        HttpEntity<Void> httpEntity = new HttpEntity<>(getHttpHeaders());
        UriComponents uriComponents = UriComponentsBuilder.fromUriString(host + "/movie/now_playing")
            .queryParam("language", "ko-KR")
            .queryParam("page", page)
            .build();
        return restTemplate
            .exchange(uriComponents.toString(), HttpMethod.GET, httpEntity, TMDBMovieResponse.class)
            .getBody();
    }

    private HttpHeaders getHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + apiKey);
        return headers;
    }

}
