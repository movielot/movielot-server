package net.movielot.movielot.response.tmdb;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class TMDBMovieListResponse {
    private Dates dates;
    private int page;
    private int totalPage;
    private int totalResults;
    private List<TMDBMovie> results;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    public static class Dates {
        private String maximum;
        private String minimum;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    public static class TMDBMovie {
        private boolean adult;
        private String backdropPath;
        private List<Long> genreIds;
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
    }
}
