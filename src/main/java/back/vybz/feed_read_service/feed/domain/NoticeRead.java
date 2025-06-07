package back.vybz.feed_read_service.feed.domain;

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
}
