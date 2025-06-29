package back.vybz.feed_read_service.kafka.consumer;

import back.vybz.feed_read_service.feed.domain.FeedRead;
import back.vybz.feed_read_service.feed.infrastructure.AboutReadRepository;
import back.vybz.feed_read_service.kafka.event.AboutCreateEvent;
import back.vybz.feed_read_service.kafka.event.AboutUpdateEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class AboutEventConsumer {

    private final AboutReadRepository aboutReadRepository;

    @KafkaListener(
            topics = "about-create",
            groupId = "feed-read-about-group",
            containerFactory = "aboutCreateKafkaListenerContainerFactory"
    )
    public void consumeAboutCreateEvent(AboutCreateEvent event) {
        log.info("🟢 어바웃 생성 이벤트 수신: {}", event);
        FeedRead feedRead = FeedRead.createAbout(
                event.getId(),
                event.getWriterUuid(),
                event.getWriterType(),
                event.getContent(),
                event.getHashTag(),
                event.getFileList()
        );
        aboutReadRepository.save(feedRead);
    }

    @KafkaListener(
            topics = "about-update",
            groupId = "feed-read-about-group",
            containerFactory = "aboutUpdateKafkaListenerContainerFactory"
    )
    public void consumeAboutUpdateEvent(AboutUpdateEvent event) {
        log.info("🟡 어바웃 수정 이벤트 수신: {}", event);
        aboutReadRepository.findById(event.getId())
                .ifPresent(feedRead -> {
                    feedRead.updateAbout(event.getContent(), event.getHashTag(), event.getFileList());
                    aboutReadRepository.save(feedRead);
                });
    }
}
