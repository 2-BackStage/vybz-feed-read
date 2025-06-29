package back.vybz.feed_read_service.feed.infrastructure;

import back.vybz.feed_read_service.feed.domain.FeedRead;
import back.vybz.feed_read_service.feed.domain.FeedType;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface FanFeedReadRepository extends MongoRepository<FeedRead, String>, FanFeedReadRepositoryCustom {
    
    // 팬 피드만 조회
    @Query("{'feedType': 'FAN_FEED'}")
    List<FeedRead> findAllFanFeeds();
}
