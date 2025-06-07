package back.vybz.feed_read_service.feed.infrastructure;

import back.vybz.feed_read_service.feed.domain.FanFeedRead;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FanFeedReadRepository extends MongoRepository<FanFeedRead, String>, FanFeedReadRepositoryCustom {
}
