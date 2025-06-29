package back.vybz.feed_read_service.feed.infrastructure;

import back.vybz.feed_read_service.feed.domain.FeedRead;
import back.vybz.feed_read_service.feed.domain.FeedType;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AboutReadRepository extends MongoRepository<FeedRead, String> {
    
    // 소개만 조회
    @Query("{'feedType': 'ABOUT'}")
    List<FeedRead> findAllAbouts();
    
    // 작성자별 소개 조회
    @Query("{'feedType': 'ABOUT', 'writerUuid': ?0}")
    Optional<FeedRead> findByWriterUuid(String writerUuid);
}
