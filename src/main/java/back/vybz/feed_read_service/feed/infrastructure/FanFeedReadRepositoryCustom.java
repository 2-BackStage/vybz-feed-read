package back.vybz.feed_read_service.feed.infrastructure;

import back.vybz.feed_read_service.feed.domain.FeedRead;
import back.vybz.feed_read_service.kafka.event.FanFeedUpdateEvent;

import java.util.List;

public interface FanFeedReadRepositoryCustom {
    List<FeedRead> findWithScroll(String sortType, String lastId, String writerUuid, int size);
}
