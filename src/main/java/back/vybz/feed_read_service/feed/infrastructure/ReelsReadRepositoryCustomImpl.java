package back.vybz.feed_read_service.feed.infrastructure;

import back.vybz.feed_read_service.feed.domain.ReelsRead;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReelsReadRepositoryCustomImpl implements ReelsReadRepositoryCustom {

    private final MongoTemplate mongoTemplate;

    @Override
    public List<ReelsRead> findWithScroll(String sortType, String lastId, String writerUuid, int size) {
        Query query = new Query();

        if (lastId != null && !lastId.isBlank()) {
            query.addCriteria(Criteria.where("_id").lt(lastId));
        }

        if (writerUuid != null && !writerUuid.isBlank()) {
            query.addCriteria(Criteria.where("writerUuid").is(writerUuid)
                    .and("writerType").is("BUSKER"));
        }

        Sort sort = switch (sortType.toUpperCase()) {
            case "LIKES" -> Sort.by(Sort.Order.desc("likeCount"), Sort.Order.desc("_id"));
            case "COMMENTS" -> Sort.by(Sort.Order.desc("commentCount"), Sort.Order.desc("_id"));
            default -> Sort.by(Sort.Order.desc("_id"));
        };

        query.with(sort).limit(size + 1);

        return mongoTemplate.find(query, ReelsRead.class);
    }
}
