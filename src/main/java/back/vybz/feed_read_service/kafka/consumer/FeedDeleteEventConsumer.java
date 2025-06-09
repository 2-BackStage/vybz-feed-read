package back.vybz.feed_read_service.kafka.consumer;

import back.vybz.feed_read_service.feed.domain.FeedType;
import back.vybz.feed_read_service.feed.infrastructure.AboutReadRepository;
import back.vybz.feed_read_service.feed.infrastructure.FanFeedReadRepository;
import back.vybz.feed_read_service.feed.infrastructure.NoticeReadRepository;
import back.vybz.feed_read_service.feed.infrastructure.ReelsReadRepository;
import back.vybz.feed_read_service.kafka.event.FeedDeleteEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class FeedDeleteEventConsumer {

    private final FanFeedReadRepository fanFeedReadRepository;
    private final ReelsReadRepository reelsReadRepository;
    private final NoticeReadRepository noticeReadRepository;
    private final AboutReadRepository aboutReadRepository;

    @KafkaListener(
            topics = "feed-delete",
            groupId = "feed-read-delete-group",
            containerFactory = "feedDeleteKafkaListenerContainerFactory"
    )
    public void consumeFeedDeleteEvent(FeedDeleteEvent event) {
        log.info("🗑️ 피드 삭제 이벤트 수신: {}", event);

        switch (event.getFeedType()) {
            case FAN_FEED -> fanFeedReadRepository.deleteById(event.getId());
            case REELS -> reelsReadRepository.deleteById(event.getId());
            case NOTICE -> noticeReadRepository.deleteById(event.getId());
            case ABOUT -> aboutReadRepository.deleteById(event.getId());
            default -> log.warn("알 수 없는 피드 타입: {}", event.getFeedType());
        }
    }
}

