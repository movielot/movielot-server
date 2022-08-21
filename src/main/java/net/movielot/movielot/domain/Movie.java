package net.movielot.movielot.domain;

import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Movie {

    // TODO. define needed fields.

    @Id
    private String id;
    private String title;
    private String thumbnail;

}
