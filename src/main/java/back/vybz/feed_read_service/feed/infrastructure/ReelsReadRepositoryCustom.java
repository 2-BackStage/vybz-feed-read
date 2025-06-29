package back.vybz.feed_read_service.feed.infrastructure;

import back.vybz.feed_read_service.feed.domain.FeedRead;

import java.util.List;

public interface ReelsReadRepositoryCustom {
    List<FeedRead> findWithScroll(String sortType, String lastId, String writerUuid, int size);
}