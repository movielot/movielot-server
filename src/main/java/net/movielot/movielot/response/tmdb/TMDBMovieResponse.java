package net.movielot.movielot.response.tmdb;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class TMDBMovieResponse {

    private Dates dates;
    private int page;
    private int totalPage;
    private int totalResults;
    private List<TMDBMovie> results;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Dates {

        private String maximum;
        private String minimum;
    }

}
