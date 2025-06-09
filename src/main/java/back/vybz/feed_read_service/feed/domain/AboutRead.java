package back.vybz.feed_read_service.feed.domain;

import back.vybz.feed_read_service.kafka.event.AboutCreateEvent;
import back.vybz.feed_read_service.kafka.event.AboutUpdateEvent;
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
@Document("about_read")
public class AboutRead {

    @Id
    private String id;

    // 작성자 UUID
    private String writerUuid;

    // 작성자 타입
    private WriterType writerType;

    // 자기소개 내용
    private String content;

    // 해시 태그
    private List<String> hashTag;

    // 파일 리스트 (이미지/영상 등)
    private List<FeedFile> fileList;

    @CreatedDate
    @Field(name = "created_at")
    private Instant createdAt;

    @LastModifiedDate
    @Field(name = "updated_at")
    private Instant updatedAt;

    @Builder
    public AboutRead(String id,
                     String writerUuid,
                     WriterType writerType,
                     String content,
                     List<String> hashTag,
                     List<FeedFile> fileList,
                     Instant createdAt,
                     Instant updatedAt) {
        this.id = id;
        this.writerUuid = writerUuid;
        this.writerType = writerType;
        this.content = content;
        this.hashTag = hashTag;
        this.fileList = fileList;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }


    public static AboutRead from(AboutCreateEvent aboutCreateEvent) {
        return AboutRead.builder()
                .id(aboutCreateEvent.getId())
                .writerUuid(aboutCreateEvent.getWriterUuid())
                .writerType(aboutCreateEvent.getWriterType())
                .content(aboutCreateEvent.getContent())
                .hashTag(aboutCreateEvent.getHashTag())
                .fileList(aboutCreateEvent.getFileList())
                .createdAt(aboutCreateEvent.getCreatedAt())
                .build();
    }

    public void updateWith(AboutUpdateEvent event) {
        this.content = event.getContent();
        this.hashTag = event.getHashTag();
        this.fileList = event.getFileList();
        this.updatedAt = Instant.now();
    }
}
