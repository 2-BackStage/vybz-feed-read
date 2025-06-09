package back.vybz.feed_read_service.feed.vo.response;

import back.vybz.feed_read_service.feed.domain.ReelsRead;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;

@Getter
@NoArgsConstructor
public class ResponseScrollReelsVo {

    private String id;
    private String writerUuid;
    private String content;
    private String thumbnailUrl;
    private String videoUrl;
    private int likeCount;
    private int commentCount;
    private Instant createdAt;

    @Builder
    public ResponseScrollReelsVo(String id,
                                 String writerUuid,
                                 String content,
                                 String thumbnailUrl,
                                 String videoUrl,
                                 int likeCount,
                                 int commentCount,
                                 Instant createdAt) {
        this.id = id;
        this.writerUuid = writerUuid;
        this.content = content;
        this.thumbnailUrl = thumbnailUrl;
        this.videoUrl = videoUrl;
        this.likeCount = likeCount;
        this.commentCount = commentCount;
        this.createdAt = createdAt;
    }

    public static ResponseScrollReelsVo from(ReelsRead feed) {
        return ResponseScrollReelsVo.builder()
                .id(feed.getId())
                .writerUuid(feed.getWriterUuid())
                .content(feed.getContent())
                .thumbnailUrl(feed.getFileList().get(0).getThumbnailUrl())
                .videoUrl(feed.getFileList().get(0).getFileUrl())
                .likeCount(feed.getLikeCount())
                .commentCount(feed.getCommentCount())
                .createdAt(feed.getCreatedAt())
                .build();
    }

    public static List<ResponseScrollReelsVo> listFrom(List<ReelsRead> reelsList) {
        return reelsList.stream()
                .map(ResponseScrollReelsVo::from)
                .toList();
    }
}
