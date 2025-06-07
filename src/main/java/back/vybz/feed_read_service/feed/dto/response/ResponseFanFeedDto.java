package back.vybz.feed_read_service.feed.dto.response;

import back.vybz.feed_read_service.feed.domain.FanFeedRead;
import back.vybz.feed_read_service.feed.domain.FeedFile;
import back.vybz.feed_read_service.feed.domain.TaggedHuman;
import back.vybz.feed_read_service.feed.vo.response.ResponseFanFeedVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;

@Getter
@NoArgsConstructor
public class ResponseFanFeedDto {

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
    public ResponseFanFeedDto(String id,
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

    public static ResponseFanFeedDto from(FanFeedRead feed) {
        return ResponseFanFeedDto.builder()
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

    public ResponseFanFeedVo toVo() {
        return ResponseFanFeedVo.builder()
                .id(id)
                .content(content)
                .location(location)
                .hashTag(hashTag)
                .humanTag(humanTag)
                .fileList(fileList)
                .likeCount(likeCount)
                .commentCount(commentCount)
                .createdAt(createdAt)
                .build();
    }
}
