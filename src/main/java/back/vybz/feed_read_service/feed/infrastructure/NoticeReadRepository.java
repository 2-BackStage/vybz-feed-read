package back.vybz.feed_read_service.feed.infrastructure;

import back.vybz.feed_read_service.feed.domain.NoticeRead;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NoticeReadRepository extends MongoRepository<NoticeRead, String>, NoticeReadRepositoryCustom {
}
