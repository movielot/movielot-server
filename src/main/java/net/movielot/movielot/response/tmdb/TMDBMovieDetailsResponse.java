package net.movielot.movielot.response.tmdb;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class TMDBMovieDetailsResponse {
    private boolean adult;
    private String backdropPath;
    private List<Genre> genres;
    private long id;
    private String originalLanguage;
    private String originalTitle;
    private String overview;
    private Double popularity;
    private String posterPath;
    private String releaseDate;
    private String title;
    private boolean video;
    private Double voteAverage;
    private int voteCount;
    private String imdbId;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Genre {
        private int id;
        private String name;
    }
}
