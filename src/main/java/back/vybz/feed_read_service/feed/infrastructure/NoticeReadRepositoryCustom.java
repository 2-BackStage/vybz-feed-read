package back.vybz.feed_read_service.feed.infrastructure;

import back.vybz.feed_read_service.feed.domain.NoticeRead;
import back.vybz.feed_read_service.kafka.event.NoticeUpdateEvent;

import java.util.List;

public interface NoticeReadRepositoryCustom {
    List<NoticeRead> findWithScroll(String sortType, String lastId, int size);
}
