package back.vybz.feed_read_service.kafka.event;

import back.vybz.feed_read_service.feed.domain.FeedType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentCountEvent {

    private String feedId;
    private FeedType feedType;
    private int totalCount;
}
