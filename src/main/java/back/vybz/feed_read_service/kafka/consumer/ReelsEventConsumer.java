package back.vybz.feed_read_service.kafka.consumer;

import back.vybz.feed_read_service.feed.domain.ReelsRead;
import back.vybz.feed_read_service.feed.infrastructure.ReelsReadRepository;
import back.vybz.feed_read_service.kafka.event.ReelsCreateEvent;
import back.vybz.feed_read_service.kafka.event.ReelsUpdateEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ReelsEventConsumer {

    private final ReelsReadRepository reelsReadRepository;

    @KafkaListener(
            topics = "reels-create",
            groupId = "create-feed-read-reels-group",
            containerFactory = "reelsCreateKafkaListenerContainerFactory"
    )
    public void consumeReelsCreateEvent(ReelsCreateEvent event) {
        log.info("🟢 릴스 생성 이벤트 수신: {}", event);
        reelsReadRepository.save(ReelsRead.from(event));
    }

    @KafkaListener(
            topics = "reels-update",
            groupId = "update-feed-read-reels-group",
            containerFactory = "reelsUpdateKafkaListenerContainerFactory"
    )
    public void consumeReelsUpdateEvent(ReelsUpdateEvent event) {
        log.info("🟡 릴스 수정 이벤트 수신: {}", event);
        reelsReadRepository.findById(event.getId())
                .ifPresent(reels -> {
                    reels.updateWith(event);
                    reelsReadRepository.save(reels);
                });
    }
}

