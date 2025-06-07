package back.vybz.feed_read_service.feed.vo.response;

import back.vybz.feed_read_service.feed.domain.FeedFile;
import back.vybz.feed_read_service.feed.domain.NoticeRead;
import back.vybz.feed_read_service.feed.domain.TaggedHuman;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class ResponseScrollNoticeVo {

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
    public ResponseScrollNoticeVo(String id,
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

    public static ResponseScrollNoticeVo from(NoticeRead noticeRead) {
        return ResponseScrollNoticeVo.builder()
                .id(noticeRead.getId())
                .title(noticeRead.getTitle())
                .content(noticeRead.getContent())
                .location(noticeRead.getLocation())
                .hashTag(noticeRead.getHashTag())
                .humanTag(noticeRead.getHumanTag())
                .fileList(noticeRead.getFileList())
                .startedAt(noticeRead.getStartedAt())
                .endedAt(noticeRead.getEndedAt())
                .likeCount(noticeRead.getLikeCount())
                .commentCount(noticeRead.getCommentCount())
                .createdAt(noticeRead.getCreatedAt())
                .build();
    }

    public static List<ResponseScrollNoticeVo> listFrom(List<NoticeRead> noticeList) {
        return noticeList.stream()
                .map(ResponseScrollNoticeVo::from)
                .collect(Collectors.toList());
    }
}
