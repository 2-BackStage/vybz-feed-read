package back.vybz.feed_read_service.feed.infrastructure;

import back.vybz.feed_read_service.feed.domain.FeedRead;
import back.vybz.feed_read_service.kafka.event.NoticeUpdateEvent;

import java.util.List;

public interface NoticeReadRepositoryCustom {
    List<FeedRead> findWithScroll(String sortType, String lastId, int size);
}
