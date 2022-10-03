package net.movielot.movielot.infrastructure;

import net.movielot.movielot.domain.Emotion;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface EmotionRepository extends ReactiveCrudRepository<Emotion, String> {

    Flux<Emotion> findByVideoId(String id);
}
