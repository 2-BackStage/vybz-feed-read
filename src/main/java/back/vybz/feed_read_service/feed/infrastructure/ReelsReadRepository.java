package back.vybz.feed_read_service.feed.infrastructure;

import back.vybz.feed_read_service.feed.domain.FeedRead;
import back.vybz.feed_read_service.feed.domain.FeedType;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface ReelsReadRepository extends MongoRepository<FeedRead, String>, ReelsReadRepositoryCustom {
    
    // 릴스만 조회
    @Query("{'feedType': 'REELS'}")
    List<FeedRead> findAllReels();
    
    // 멤버십 필요 없는 릴스 조회
    @Query("{'feedType': 'REELS', 'memberShip': false}")
    List<FeedRead> findFreeReels();
}