package back.vybz.feed_read_service.feed.domain;

import back.vybz.feed_read_service.kafka.event.NoticeCreateEvent;
import back.vybz.feed_read_service.kafka.event.NoticeUpdateEvent;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.Instant;
import java.util.List;

@Getter
@NoArgsConstructor
@Document("notice_read")
public class NoticeRead {

    @Id
    private String id;
    //작성자 uuid
    private String writerUuid;
    // 작성자 타입
    private WriterType writerType;
    //공지 제목
    private String title;
    // 피드 내용
    private String content;
    // 위치
    private String location;
    // 해시 태그
    private List<String> hashTag;
    // 사람 태그
    private List<TaggedHuman> humanTag;
    // 파일 리스트
    private List<FeedFile> fileList;
    // 시작 일시
    private Instant startedAt;
    // 종료 일시
    private Instant endedAt;
    //좋아요 수
    private int likeCount;
    //댓글 수
    private int commentCount;

    @CreatedDate
    @Field(name = "created_at")
    private Instant createdAt;

    @LastModifiedDate
    @Field(name = "updated_at")
    private Instant updatedAt;

    @Builder
    public NoticeRead(String id,
                      String writerUuid,
                      WriterType writerType,
                      String title,
                      String content,
                      String location,
                      List<String> hashTag,
                      List<TaggedHuman> humanTag,
                      List<FeedFile> fileList,
                      Instant startedAt,
                      Instant endedAt,
                      int likeCount,
                      int commentCount) {
        this.id = id;
        this.writerUuid = writerUuid;
        this.writerType = writerType;
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
    }


    public static NoticeRead from(NoticeCreateEvent noticeCreateEvent) {
        return NoticeRead.builder()
                .id(noticeCreateEvent.getId())
                .writerUuid(noticeCreateEvent.getWriterUuid())
                .writerType(noticeCreateEvent.getWriterType())
                .title(noticeCreateEvent.getTitle())
                .content(noticeCreateEvent.getContent())
                .location(noticeCreateEvent.getLocation())
                .hashTag(noticeCreateEvent.getHashTag())
                .humanTag(noticeCreateEvent.getHumanTag())
                .fileList(noticeCreateEvent.getFileList())
                .startedAt(noticeCreateEvent.getStartedAt())
                .endedAt(noticeCreateEvent.getEndedAt())
                .likeCount(0)
                .commentCount(0)
                .build();
    }
    public void updateWith(NoticeUpdateEvent event) {
        this.title = event.getTitle();
        this.content = event.getContent();
        this.location = event.getLocation();
        this.hashTag = event.getHashTag();
        this.humanTag = event.getHumanTag();
        this.fileList = event.getFileList();
        this.startedAt = event.getStartedAt();
        this.endedAt = event.getEndedAt();
        this.updatedAt = Instant.now();
    }
    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }
    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }


}
