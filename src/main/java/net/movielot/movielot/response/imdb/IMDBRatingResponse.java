package net.movielot.movielot.response.imdb;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class IMDBRatingResponse {
    @JsonProperty("imDbId")
    private String imdbId;
    private String title;
    private String fullTitle;
    private String type;
    private String year;
    @JsonProperty("imDb")
    private String imdb;
    private String metacritic;
    private String theMovieDb;
    private String rottenTomatoes;
    private String filmAffinity;
    private String errorMessage;
}
