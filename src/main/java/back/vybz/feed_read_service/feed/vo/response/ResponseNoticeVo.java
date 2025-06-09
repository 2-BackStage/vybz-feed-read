package back.vybz.feed_read_service.feed.vo.response;

import back.vybz.feed_read_service.feed.domain.FeedFile;
import back.vybz.feed_read_service.feed.domain.TaggedHuman;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;

@Getter
@NoArgsConstructor
public class ResponseNoticeVo {

    private String id;
    private String title;
    private String content;
    private String location;
    private List<String> hashTag;
    private List<TaggedHuman> humanTag;
    private List<FeedFile> fileList;
    private Instant startedAt;
    private Instant endedAt;
    private int likeCount;
    private int commentCount;
    private Instant createdAt;

    @Builder
    public ResponseNoticeVo(String id,
                            String title,
                            String content,
                            String location,
                            List<String> hashTag,
                            List<TaggedHuman> humanTag,
                            List<FeedFile> fileList,
                            Instant startedAt,
                            Instant endedAt,
                            int likeCount,
                            int commentCount,
                            Instant createdAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.location = location;
        this.hashTag = hashTag;
        this.humanTag = humanTag;
        this.fileList = fileList;
        this.startedAt = startedAt;
        this.endedAt = endedAt;
        this.likeCount = likeCount;
        this.commentCount = commentCount;
        this.createdAt = createdAt;
    }
}
