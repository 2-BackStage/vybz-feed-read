package back.vybz.feed_read_service.kafka.event;


import back.vybz.feed_read_service.feed.domain.FeedFile;
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
public class AboutUpdateEvent {

    private String id;
    private String writerUuid;
    private WriterType writerType;
    private String content;
    private List<String> hashTag;
    private List<FeedFile> fileList;
    private Instant updatedAt;

}
