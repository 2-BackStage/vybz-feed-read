package back.vybz.feed_read_service.feed.infrastructure;

import back.vybz.feed_read_service.feed.domain.FanFeedRead;

import java.util.List;

public interface FanFeedReadRepositoryCustom {
    List<FanFeedRead> findWithScroll(String sortType, String lastId, String writerUuid, int size);
}
