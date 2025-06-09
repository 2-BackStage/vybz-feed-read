package back.vybz.feed_read_service.kafka.event;


import back.vybz.feed_read_service.feed.domain.FeedType;
import back.vybz.feed_read_service.feed.domain.WriterType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FeedDeleteEvent {

    private String id;
    private String writerUuid;
    private WriterType writerType;
    private FeedType feedType;


}
