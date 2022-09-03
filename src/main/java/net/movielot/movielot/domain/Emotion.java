package net.movielot.movielot.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.movielot.movielot.enums.EmotionConstant;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Emotion {
    private int videoId;
    private EmotionConstant type;
    private LocalDateTime createdAt;
}
