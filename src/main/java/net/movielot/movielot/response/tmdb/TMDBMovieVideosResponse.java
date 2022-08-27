package net.movielot.movielot.response.tmdb;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class TMDBMovieVideosResponse {
    private int id;
    private List<Video> results;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Video {
        private String name;
        private String key;
        private String site;
        private int size;
        private String type;
        private boolean official;
        private String id;
    }
}
