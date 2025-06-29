package back.vybz.feed_read_service.feed.infrastructure;

import back.vybz.feed_read_service.feed.domain.FeedRead;
import back.vybz.feed_read_service.feed.domain.FeedType;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.time.Instant;
import java.util.List;

public interface NoticeReadRepository extends MongoRepository<FeedRead, String>, NoticeReadRepositoryCustom {

    // 공지사항만 조회
    @Query("{'feedType': 'NOTICE'}")
    List<FeedRead> findAllNotices();
    
    // 멤버십 필요 없는 공지사항 조회
    @Query("{'feedType': 'NOTICE', 'memberShip': false}")
    List<FeedRead> findFreeNotices();
    
    // 활성 공지사항 조회
    @Query("{'feedType': 'NOTICE', 'startedAt': {$lte: ?0}, 'endedAt': {$gte: ?0}}")
    List<FeedRead> findActiveNotices(Instant currentTime);
}
