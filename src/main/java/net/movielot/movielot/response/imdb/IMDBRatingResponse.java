package net.movielot.movielot.response.imdb;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.movielot.movielot.domain.Movie;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class IMDBRatingResponse {
    private String imDbId;
    private String title;
    private String fullTitle;
    private String type;
    private String year;
    private String imDb;
    private String metacritic;
    private String theMovieDb;
    private String rottenTomatoes;
    private String filmAffinity;
    private String errorMessage;
}
