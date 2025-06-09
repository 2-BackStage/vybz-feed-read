package back.vybz.feed_read_service.feed.domain;

import back.vybz.feed_read_service.kafka.event.ReelsCreateEvent;
import back.vybz.feed_read_service.kafka.event.ReelsUpdateEvent;
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
@Document("reels_read")
public class ReelsRead {

    @Id
    private String id;
    //작성자 uuid
    private String writerUuid;
    // 작성자 타입
    private WriterType writerType;
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
    public ReelsRead(String id,
                     String writerUuid,
                     WriterType writerType,
                     String content,
                     String location,
                     List<String> hashTag,
                     List<TaggedHuman> humanTag,
                     List<FeedFile> fileList,
                     int likeCount,
                     int commentCount) {
        this.id = id;
        this.writerUuid = writerUuid;
        this.writerType = writerType;
        this.content = content;
        this.location = location;
        this.hashTag = hashTag;
        this.humanTag = humanTag;
        this.fileList = fileList;
        this.likeCount = likeCount;
        this.commentCount = commentCount;
    }

    public static ReelsRead from(ReelsCreateEvent reelsCreateEvent) {
        return ReelsRead.builder()
                .id(reelsCreateEvent.getId())
                .writerUuid(reelsCreateEvent.getWriterUuid())
                .writerType(reelsCreateEvent.getWriterType())
                .content(reelsCreateEvent.getContent())
                .location(reelsCreateEvent.getLocation())
                .hashTag(reelsCreateEvent.getHashTag())
                .humanTag(reelsCreateEvent.getHumanTag())
                .fileList(reelsCreateEvent.getFileList())
                .likeCount(0)
                .commentCount(0)
                .build();
    }

    public void updateWith(ReelsUpdateEvent reelsUpdateEvent) {
        this.content = reelsUpdateEvent.getContent();
        this.location = reelsUpdateEvent.getLocation();
        this.hashTag = reelsUpdateEvent.getHashTag();
        this.humanTag = reelsUpdateEvent.getHumanTag();
        this.fileList = reelsUpdateEvent.getFileList();
        this.updatedAt = Instant.now();
    }
    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }
    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }



}
