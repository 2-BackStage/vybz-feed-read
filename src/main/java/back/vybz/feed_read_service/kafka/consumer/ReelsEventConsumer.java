package back.vybz.feed_read_service.kafka.consumer;

import back.vybz.feed_read_service.feed.domain.FeedRead;
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
            groupId = "feed-read-reels-group",
            containerFactory = "reelsCreateKafkaListenerContainerFactory"
    )
    public void consumeReelsCreateEvent(ReelsCreateEvent event) {
        log.info("🟢 릴스 생성 이벤트 수신: {}", event);
        FeedRead feedRead = FeedRead.createReels(
                event.getId(),
                event.getWriterUuid(),
                event.getWriterType(),
                event.getContent(),
                event.getLocation(),
                event.getHashTag(),
                event.getHumanTag(),
                event.getFileList(),
                event.isMemberShip() // 이벤트에서 멤버십 정보 가져오기
        );
        reelsReadRepository.save(feedRead);
    }

    @KafkaListener(
            topics = "reels-update",
            groupId = "feed-read-reels-group",
            containerFactory = "reelsUpdateKafkaListenerContainerFactory"
    )
    public void consumeReelsUpdateEvent(ReelsUpdateEvent event) {
        log.info("🟡 릴스 수정 이벤트 수신: {}", event);
        reelsReadRepository.findById(event.getId())
                .ifPresent(feedRead -> {
                    feedRead.updateReels(event.getContent(), event.getLocation(), event.getHashTag(), event.getHumanTag(), event.getFileList());
                    reelsReadRepository.save(feedRead);
                });
    }
}

