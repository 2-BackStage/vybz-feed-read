package back.vybz.feed_read_service.kafka.consumer;

import back.vybz.feed_read_service.feed.domain.FeedRead;
import back.vybz.feed_read_service.feed.infrastructure.NoticeReadRepository;
import back.vybz.feed_read_service.kafka.event.NoticeCreateEvent;
import back.vybz.feed_read_service.kafka.event.NoticeUpdateEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class NoticeEventConsumer {

    private final NoticeReadRepository noticeReadRepository;

    @KafkaListener(
            topics = "notice-create",
            groupId = "create-feed-read-notice-group",
            containerFactory = "noticeCreateKafkaListenerContainerFactory"
    )
    public void consumeNoticeCreateEvent(NoticeCreateEvent event) {
        log.info("🟢 공지 생성 이벤트 수신: {}", event);
        FeedRead feedRead = FeedRead.createNotice(
                event.getId(),
                event.getWriterUuid(),
                event.getWriterType(),
                event.getTitle(),
                event.getContent(),
                event.getLocation(),
                event.getHashTag(),
                event.getHumanTag(),
                event.getFileList(),
                event.getStartedAt(),
                event.getEndedAt(),
                event.isMemberShip() // 이벤트에서 멤버십 정보 가져오기
        );
        noticeReadRepository.save(feedRead);
    }

    @KafkaListener(
            topics = "notice-update",
            groupId = "update-feed-read-notice-group",
            containerFactory = "noticeUpdateKafkaListenerContainerFactory"
    )
    public void consumeNoticeUpdateEvent(NoticeUpdateEvent event) {
        log.info("🟡 공지 수정 이벤트 수신: {}", event);
        noticeReadRepository.findById(event.getId())
                .ifPresent(feedRead -> {
                    feedRead.updateNotice(event.getTitle(), event.getContent(), event.getLocation(), event.getHashTag(), event.getHumanTag(), event.getFileList(), event.getStartedAt(), event.getEndedAt());
                    noticeReadRepository.save(feedRead);
                });
    }
}
