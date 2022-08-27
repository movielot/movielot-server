package net.movielot.movielot.configuration;

import lombok.extern.slf4j.Slf4j;
import net.movielot.movielot.response.imdb.IMDBRatingResponse;
import net.movielot.movielot.response.tmdb.TMDBMovieDetailsResponse;
import net.movielot.movielot.response.tmdb.TMDBMovieVideosResponse;
import net.movielot.movielot.response.tmdb.TMDBProvidersResponse;
import net.movielot.movielot.response.tmdb.TMDBMovieListResponse;
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

    public TMDBMovieListResponse getLatestMovies(int page) {
        HttpEntity<Void> httpEntity = new HttpEntity<>(getHttpHeaders());
        UriComponents uriComponents = UriComponentsBuilder.fromUriString(host + "/movie/now_playing")
            .queryParam("language", "ko-KR")
            .queryParam("page", page)
            .build();
        return restTemplate
            .exchange(uriComponents.toString(), HttpMethod.GET, httpEntity, TMDBMovieListResponse.class)
            .getBody();
    }

    public TMDBProvidersResponse getProviders(String id) {
        HttpEntity<Void> httpEntity = new HttpEntity<>(getHttpHeaders());
        UriComponents uriComponents = UriComponentsBuilder.fromUriString(host + "/movie/" + id + "/watch/providers")
            .build();
        return restTemplate
            .exchange(uriComponents.toString(), HttpMethod.GET, httpEntity, TMDBProvidersResponse.class)
            .getBody();
    }

    public TMDBMovieDetailsResponse getDetails(String id) {
        HttpEntity<Void> httpEntity = new HttpEntity<>(getHttpHeaders());
        UriComponents uriComponents = UriComponentsBuilder.fromUriString(host + "/movie/" + id)
                .queryParam("language", "ko")
                .build();
        return restTemplate
                .exchange(uriComponents.toString(), HttpMethod.GET, httpEntity, TMDBMovieDetailsResponse.class)
                .getBody();
    }

    public TMDBMovieVideosResponse getVideos(String id) {
        HttpEntity<Void> httpEntity = new HttpEntity<>(getHttpHeaders());
        UriComponents uriComponents = UriComponentsBuilder.fromUriString(host + "/movie/" + id + "/videos")
                .queryParam("language", "ko")
                .build();
        return restTemplate
                .exchange(uriComponents.toString(), HttpMethod.GET, httpEntity, TMDBMovieVideosResponse.class)
                .getBody();
    }

    private HttpHeaders getHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + apiKey);
        return headers;
    }
}
