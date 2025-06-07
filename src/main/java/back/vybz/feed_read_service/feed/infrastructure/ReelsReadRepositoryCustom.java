package back.vybz.feed_read_service.feed.infrastructure;

import back.vybz.feed_read_service.feed.domain.ReelsRead;

import java.util.List;

public interface ReelsReadRepositoryCustom {
    List<ReelsRead> findWithScroll(String sortType, String lastId, String writerUuid, int size);
}