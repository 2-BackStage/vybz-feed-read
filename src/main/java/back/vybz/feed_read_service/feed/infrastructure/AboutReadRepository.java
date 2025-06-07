package back.vybz.feed_read_service.feed.infrastructure;

import back.vybz.feed_read_service.feed.domain.AboutRead;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface AboutReadRepository extends MongoRepository<AboutRead, String> {
}
