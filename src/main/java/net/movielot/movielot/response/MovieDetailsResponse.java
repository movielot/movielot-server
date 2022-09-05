package net.movielot.movielot.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.movielot.movielot.response.tmdb.TMDBMovieDetailsResponse;
import net.movielot.movielot.response.tmdb.TMDBMovieVideosResponse;
import net.movielot.movielot.response.tmdb.TMDBProvidersResponse;
import org.springframework.beans.BeanUtils;

import java.util.LinkedList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class MovieDetailsResponse {
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
    private TMDBVideo videos;
    private TMDBProvider providers;

    public void setGenres(List<TMDBMovieDetailsResponse.Genre> genres) {
        this.genres = new LinkedList<>();
        genres.forEach(result -> {
            Genre genre = new Genre();
            BeanUtils.copyProperties(result, genre);
            this.genres.add(genre);
        } );
    }

    public void setVideos(TMDBVideo videos) {
        this.videos = videos;
    }

    public void setVideos(TMDBMovieVideosResponse videos) {
        BeanUtils.copyProperties(videos, this.videos);
    }

    public void setProviders(TMDBProvider providers) {
        this.providers = providers;
    }

    public void setProviders(TMDBProvidersResponse providers) {
        BeanUtils.copyProperties(providers, this.providers);
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Genre {
        private int id;
        private String name;
    }


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class TMDBProvider {
        private int id;
        private Countries results;

        public void setResults(TMDBProvidersResponse.Countries results) {
            this.results = new Countries();
            BeanUtils.copyProperties(results, this.results);
        }

        @Data
        @AllArgsConstructor
        @NoArgsConstructor
        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class Provider {
            private int displayPriority;
            private String logoPath;
            private String providerName;
            private int providerId;
        }

        @Data
        @AllArgsConstructor
        @NoArgsConstructor
        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class Country {
            private String link;
            private List<Provider> buy;
            private List<Provider> rent;
            private List<Provider> flatrate;

            public void setBuy(List<TMDBProvidersResponse.Provider> buy) {
                if(buy != null) {
                    this.buy = new LinkedList<>();
                    buy.forEach(result -> {
                        Provider provider = new Provider();
                        BeanUtils.copyProperties(result, provider);
                        this.buy.add(provider);
                    } );
                }
            }

            public void setRent(List<TMDBProvidersResponse.Provider> rent) {
                if(rent != null) {
                    this.rent = new LinkedList<>();
                    rent.forEach(result -> {
                        Provider provider = new Provider();
                        BeanUtils.copyProperties(result, provider);
                        this.rent.add(provider);
                    } );
                }
            }

            public void setFlatrate(List<TMDBProvidersResponse.Provider> flatrate) {
                if(flatrate != null) {
                    this.flatrate = new LinkedList<>();
                    flatrate.forEach(result -> {
                        Provider provider = new Provider();
                        BeanUtils.copyProperties(result, provider);
                        this.flatrate.add(provider);
                    } );
                }
            }
        }

        @Data
        @AllArgsConstructor
        @NoArgsConstructor
        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class Countries {
            @JsonProperty("KR")
            private Country KR;

            public void setKR(TMDBProvidersResponse.Country KR) {
                this.KR = new Country();
                BeanUtils.copyProperties(KR, this.KR);
            }
        }
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class TMDBVideo {
        private int id;
        private List<Video> results;

        public void setResults(List<net.movielot.movielot.response.tmdb.TMDBMovieVideosResponse.Video> results) {
            this.results = new LinkedList<>();
            results.forEach(result -> {
                Video video = new Video();
                BeanUtils.copyProperties(result, video);
                this.results.add(video);
            } );
        }

        @Data
        @AllArgsConstructor
        @NoArgsConstructor
        @JsonIgnoreProperties(ignoreUnknown = true)
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
}
