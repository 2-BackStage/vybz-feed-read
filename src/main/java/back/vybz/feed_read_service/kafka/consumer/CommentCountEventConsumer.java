package back.vybz.feed_read_service.kafka.consumer;

import back.vybz.feed_read_service.feed.infrastructure.FanFeedReadRepository;
import back.vybz.feed_read_service.feed.infrastructure.NoticeReadRepository;
import back.vybz.feed_read_service.feed.infrastructure.ReelsReadRepository;
import back.vybz.feed_read_service.kafka.event.CommentCountEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CommentCountEventConsumer {

    private final FanFeedReadRepository fanFeedReadRepository;
    private final ReelsReadRepository reelsReadRepository;
    private final NoticeReadRepository noticeReadRepository;

    @KafkaListener(
            topics = "comment-count",
            groupId = "comment-count-group",
            containerFactory = "commentCountKafkaListenerContainerFactory"
    )
    public void consumeCommentCountEvent(CommentCountEvent event) {
        log.info("🔥 댓글 카운트 이벤트 수신: {}", event);

        int totalCount = Math.max(0, event.getTotalCount());

        switch (event.getFeedType()) {
            case FAN_FEED -> fanFeedReadRepository.findById(event.getFeedId())
                    .ifPresent(feed -> {
                        feed.setCommentCount(totalCount);
                        fanFeedReadRepository.save(feed);
                    });

            case REELS -> reelsReadRepository.findById(event.getFeedId())
                    .ifPresent(feed -> {
                        feed.setCommentCount(totalCount);
                        reelsReadRepository.save(feed);
                    });

            case NOTICE -> noticeReadRepository.findById(event.getFeedId())
                    .ifPresent(feed -> {
                        feed.setCommentCount(totalCount);
                        noticeReadRepository.save(feed);
                    });

            default -> log.warn("❗ 알 수 없는 피드 타입: {}", event.getFeedType());
        }
    }
}
