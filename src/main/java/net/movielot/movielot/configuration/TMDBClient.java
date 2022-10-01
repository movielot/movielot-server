package net.movielot.movielot.configuration;

import lombok.extern.slf4j.Slf4j;
import net.movielot.movielot.exceptions.NoSuchElementFoundException;
import net.movielot.movielot.response.tmdb.TMDBMovieDetailsResponse;
import net.movielot.movielot.response.tmdb.TMDBMovieListResponse;
import net.movielot.movielot.response.tmdb.TMDBMovieVideosResponse;
import net.movielot.movielot.response.tmdb.TMDBProvidersResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.net.URI;

@Component
@Slf4j
public class TMDBClient {

    @Value("${client.tmdb.apiKey}")
    private String apiKey;
    @Value("${client.tmdb.host}")
    private String host;
    private final RestTemplate restTemplate = new RestTemplate();
    private final WebClient webClient = WebClient.builder()
            .baseUrl(host)
            .build();

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

    public Mono<TMDBProvidersResponse> getProviders(String id) {
        URI uri = UriComponentsBuilder.fromUriString(host + "/movie/{id}/watch/providers")
            .build(id);

        return webClient.get()
                .uri(uri)
                .headers(this::getHeaders)
                .exchangeToMono(response -> {
                    HttpStatus httpStatus = response.statusCode();

                    if(httpStatus.is4xxClientError()){
                        throw new NoSuchElementFoundException("no such element");
                    }
                    return response.bodyToMono(TMDBProvidersResponse.class);
                });
    }

    public Mono<TMDBMovieDetailsResponse> getDetails(String id) {
        URI uri = UriComponentsBuilder.fromUriString(host + "/movie/{id}")
                .queryParam("language", "ko")
                .build(id);

        return webClient.get()
                .uri(uri)
                .headers(this::getHeaders)
                .exchangeToMono(response -> {
                    HttpStatus httpStatus = response.statusCode();

                    if(httpStatus.is4xxClientError()){
                        throw new NoSuchElementFoundException("no such element");
                    }
                    return response.bodyToMono(TMDBMovieDetailsResponse.class);
                });
    }

    public Mono<TMDBMovieVideosResponse> getVideos(String id) {
        URI uri = UriComponentsBuilder.fromUriString(host + "/movie/{id}/videos")
                .queryParam("language", "ko")
                .build(id);

        return webClient.get()
                .uri(uri)
                .headers(this::getHeaders)
                .exchangeToMono(response -> {
                    HttpStatus httpStatus = response.statusCode();

                    if(httpStatus.is4xxClientError()){
                        throw new NoSuchElementFoundException("no such element");
                    }
                    return response.bodyToMono(TMDBMovieVideosResponse.class);
                });
    }

    private HttpHeaders getHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + apiKey);
        return headers;
    }

    private void getHeaders(HttpHeaders httpHeaders) {
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setBearerAuth(apiKey);
    }
}
