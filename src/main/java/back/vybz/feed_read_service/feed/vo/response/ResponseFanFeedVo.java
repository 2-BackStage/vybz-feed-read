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
public class ResponseFanFeedVo {

    private String id;
    private String content;
    private String location;
    private List<String> hashTag;
    private List<TaggedHuman> humanTag;
    private List<FeedFile> fileList;
    private int likeCount;
    private int commentCount;
    private Instant createdAt;

    @Builder
    public ResponseFanFeedVo(String id,
                             String content,
                             String location,
                             List<String> hashTag,
                             List<TaggedHuman> humanTag,
                             List<FeedFile> fileList,
                             int likeCount,
                             int commentCount,
                             Instant createdAt) {
        this.id = id;
        this.content = content;
        this.location = location;
        this.hashTag = hashTag;
        this.humanTag = humanTag;
        this.fileList = fileList;
        this.likeCount = likeCount;
        this.commentCount = commentCount;
        this.createdAt = createdAt;
    }
}
