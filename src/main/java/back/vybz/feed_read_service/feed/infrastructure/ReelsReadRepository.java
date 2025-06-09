package back.vybz.feed_read_service.feed.infrastructure;

import back.vybz.feed_read_service.feed.domain.ReelsRead;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ReelsReadRepository extends MongoRepository<ReelsRead, String>, ReelsReadRepositoryCustom {
}