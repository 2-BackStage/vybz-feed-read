package back.vybz.feed_read_service.kafka.consumer;

import back.vybz.feed_read_service.feed.domain.FeedRead;
import back.vybz.feed_read_service.feed.infrastructure.FanFeedReadRepository;
import back.vybz.feed_read_service.kafka.event.FanFeedCreateEvent;
import back.vybz.feed_read_service.kafka.event.FanFeedUpdateEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class FanFeedEventConsumer {

    private final FanFeedReadRepository fanFeedReadRepository;

    @KafkaListener(
            topics = "fanfeed-create",
            groupId = "create-feed-read-fanfeed-group",
            containerFactory = "fanFeedCreateKafkaListenerContainerFactory"
    )
    public void consumeFanFeedCreateEvent(FanFeedCreateEvent event) {
        log.info("🟢 팬피드 생성 이벤트 수신: {}", event);
        FeedRead feedRead = FeedRead.createFanFeed(
                event.getId(),
                event.getWriterUuid(),
                event.getWriterType(),
                event.getContent(),
                event.getLocation(),
                event.getHashTag(),
                event.getHumanTag(),
                event.getFileList()
        );
        fanFeedReadRepository.save(feedRead);
    }

    @KafkaListener(
            topics = "fanfeed-update",
            groupId = "update-feed-read-fanfeed-group",
            containerFactory = "fanFeedUpdateKafkaListenerContainerFactory"
    )
    public void consumeFanFeedUpdateEvent(FanFeedUpdateEvent event) {
        log.info("🟡 팬피드 수정 이벤트 수신: {}", event);
        fanFeedReadRepository.findById(event.getId())
                .ifPresent(feedRead -> {
                    feedRead.updateFanFeed(event.getContent(), event.getLocation(), event.getHashTag(), event.getHumanTag(), event.getFileList());
                    fanFeedReadRepository.save(feedRead);
                });
    }
}
