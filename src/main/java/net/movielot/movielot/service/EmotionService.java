package net.movielot.movielot.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.movielot.movielot.configuration.TMDBClient;
import net.movielot.movielot.domain.Emotion;
import net.movielot.movielot.enums.EmotionConstant;
import net.movielot.movielot.infrastructure.EmotionRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmotionService {
    private final EmotionRepository emotionRepository;
    private final TMDBClient tmdbClient;

    public void expressEmotion(String videoId, EmotionConstant emotion) {
        tmdbClient.getVideos(videoId).block();
        Emotion emotionEntity = Emotion.builder()
                .videoId(videoId)
                .type(emotion)
                .createdAt(LocalDateTime.now())
                .build();

        emotionRepository.save(emotionEntity).subscribe();
    }

    public List<Emotion> getEmotionsFor(String videoId) {
        tmdbClient.getVideos(videoId).block();
        return emotionRepository.findByVideoId(videoId).collectList().block();
    }
}
