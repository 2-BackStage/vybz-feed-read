package back.vybz.feed_read_service.kafka.event;

import back.vybz.feed_read_service.feed.domain.FeedType;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FeedLikeCountResultEvent {
    private String feedId;
    private FeedType feedType;
    private int totalLikeCount;
    private String displayLikeCount;
}
