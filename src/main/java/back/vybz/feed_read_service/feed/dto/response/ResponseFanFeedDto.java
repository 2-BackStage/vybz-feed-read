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

    public static ResponseFanFeedDto from(FanFeedRead fanFeedRead) {
        return ResponseFanFeedDto.builder()
                .id(fanFeedRead.getId())
                .content(fanFeedRead.getContent())
                .location(fanFeedRead.getLocation())
                .hashTag(fanFeedRead.getHashTag())
                .humanTag(fanFeedRead.getHumanTag())
                .fileList(fanFeedRead.getFileList())
                .likeCount(fanFeedRead.getLikeCount())
                .commentCount(fanFeedRead.getCommentCount())
                .createdAt(fanFeedRead.getCreatedAt())
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
