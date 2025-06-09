package back.vybz.feed_read_service.kafka.event;

import back.vybz.feed_read_service.feed.domain.FeedFile;
import back.vybz.feed_read_service.feed.domain.TaggedHuman;
import back.vybz.feed_read_service.feed.domain.WriterType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReelsCreateEvent {

    private String id;
    private String writerUuid;
    private WriterType writerType;
    private String content;
    private String location;
    private List<String> hashTag;
    private List<TaggedHuman> humanTag;
    private List<FeedFile> fileList;
    private Instant createdAt;


}
