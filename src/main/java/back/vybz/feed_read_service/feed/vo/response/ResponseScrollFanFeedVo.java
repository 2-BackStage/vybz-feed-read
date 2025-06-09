package back.vybz.feed_read_service.feed.vo.response;

import back.vybz.feed_read_service.feed.domain.FanFeedRead;
import back.vybz.feed_read_service.feed.domain.FeedFile;
import back.vybz.feed_read_service.feed.domain.TaggedHuman;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class ResponseScrollFanFeedVo {

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
    public ResponseScrollFanFeedVo(String id,
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

    public static ResponseScrollFanFeedVo from(FanFeedRead feed) {
        return ResponseScrollFanFeedVo.builder()
                .id(feed.getId())
                .content(feed.getContent())
                .location(feed.getLocation())
                .hashTag(feed.getHashTag())
                .humanTag(feed.getHumanTag())
                .fileList(feed.getFileList())
                .likeCount(feed.getLikeCount())
                .commentCount(feed.getCommentCount())
                .createdAt(feed.getCreatedAt())
                .build();
    }

    public static List<ResponseScrollFanFeedVo> listFrom(List<FanFeedRead> feedList) {
        return feedList.stream()
                .map(ResponseScrollFanFeedVo::from)
                .collect(Collectors.toList());
    }
}
