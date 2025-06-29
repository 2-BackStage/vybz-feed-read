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
@Document("feed_read")
public class FeedRead {

    @Id
    private String id;
    
    // 피드 타입 (REELS, NOTICE, ABOUT, FAN_FEED)
    private FeedType feedType;
    
    // 작성자 UUID
    private String writerUuid;
    
    // 작성자 타입
    private WriterType writerType;
    
    // 제목 (공지사항용)
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
    
    // 시작 일시 (공지사항용)
    private Instant startedAt;
    
    // 종료 일시 (공지사항용)
    private Instant endedAt;
    
    // 좋아요 수
    private int likeCount;
    
    // 댓글 수
    private int commentCount;
    
    // 멤버십 필요 여부 (공지사항, 릴스용)
    private boolean memberShip;
    
    @CreatedDate
    @Field(name = "created_at")
    private Instant createdAt;
    
    @LastModifiedDate
    @Field(name = "updated_at")
    private Instant updatedAt;

    @Builder
    public FeedRead(String id,
                   FeedType feedType,
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
                   int commentCount,
                   boolean memberShip,
                   Instant createdAt,
                   Instant updatedAt) {
        this.id = id;
        this.feedType = feedType;
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
        this.memberShip = memberShip;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // 팩토리 메서드들
    public static FeedRead createFanFeed(String id, String writerUuid, WriterType writerType,
                                       String content, String location, List<String> hashTag,
                                       List<TaggedHuman> humanTag, List<FeedFile> fileList) {
        return FeedRead.builder()
                .id(id)
                .feedType(FeedType.FAN_FEED)
                .writerUuid(writerUuid)
                .writerType(writerType)
                .content(content)
                .location(location)
                .hashTag(hashTag)
                .humanTag(humanTag)
                .fileList(fileList)
                .likeCount(0)
                .commentCount(0)
                .memberShip(false) // 팬 피드는 멤버십 불필요
                .build();
    }

    public static FeedRead createNotice(String id, String writerUuid, WriterType writerType,
                                      String title, String content, String location,
                                      List<String> hashTag, List<TaggedHuman> humanTag,
                                      List<FeedFile> fileList, Instant startedAt, Instant endedAt,
                                      boolean memberShip) {
        return FeedRead.builder()
                .id(id)
                .feedType(FeedType.NOTICE)
                .writerUuid(writerUuid)
                .writerType(writerType)
                .title(title)
                .content(content)
                .location(location)
                .hashTag(hashTag)
                .humanTag(humanTag)
                .fileList(fileList)
                .startedAt(startedAt)
                .endedAt(endedAt)
                .likeCount(0)
                .commentCount(0)
                .memberShip(memberShip)
                .build();
    }

    public static FeedRead createReels(String id, String writerUuid, WriterType writerType,
                                     String content, String location, List<String> hashTag,
                                     List<TaggedHuman> humanTag, List<FeedFile> fileList,
                                     boolean memberShip) {
        return FeedRead.builder()
                .id(id)
                .feedType(FeedType.REELS)
                .writerUuid(writerUuid)
                .writerType(writerType)
                .content(content)
                .location(location)
                .hashTag(hashTag)
                .humanTag(humanTag)
                .fileList(fileList)
                .likeCount(0)
                .commentCount(0)
                .memberShip(memberShip)
                .build();
    }

    public static FeedRead createAbout(String id, String writerUuid, WriterType writerType,
                                     String content, List<String> hashTag, List<FeedFile> fileList) {
        return FeedRead.builder()
                .id(id)
                .feedType(FeedType.ABOUT)
                .writerUuid(writerUuid)
                .writerType(writerType)
                .content(content)
                .hashTag(hashTag)
                .fileList(fileList)
                .likeCount(0)
                .commentCount(0)
                .memberShip(false) // 소개는 멤버십 불필요
                .build();
    }

    // 업데이트 메서드들
    public void updateFanFeed(String content, String location, List<String> hashTag,
                            List<TaggedHuman> humanTag, List<FeedFile> fileList) {
        this.content = content;
        this.location = location;
        this.hashTag = hashTag;
        this.humanTag = humanTag;
        this.fileList = fileList;
        this.updatedAt = Instant.now();
    }

    public void updateNotice(String title, String content, String location, List<String> hashTag,
                           List<TaggedHuman> humanTag, List<FeedFile> fileList,
                           Instant startedAt, Instant endedAt) {
        this.title = title;
        this.content = content;
        this.location = location;
        this.hashTag = hashTag;
        this.humanTag = humanTag;
        this.fileList = fileList;
        this.startedAt = startedAt;
        this.endedAt = endedAt;
        this.updatedAt = Instant.now();
    }

    public void updateReels(String content, String location, List<String> hashTag,
                          List<TaggedHuman> humanTag, List<FeedFile> fileList) {
        this.content = content;
        this.location = location;
        this.hashTag = hashTag;
        this.humanTag = humanTag;
        this.fileList = fileList;
        this.updatedAt = Instant.now();
    }

    public void updateAbout(String content, List<String> hashTag, List<FeedFile> fileList) {
        this.content = content;
        this.hashTag = hashTag;
        this.fileList = fileList;
        this.updatedAt = Instant.now();
    }

    // 카운트 업데이트
    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    // 멤버십 필요 여부 체크
    public boolean isMemberShipRequired() {
        return this.memberShip;
    }

    // 멤버십 접근 권한 확인
    public boolean hasMemberShipAccess(boolean isMember) {
        if (!this.memberShip) {
            return true; // 멤버십이 필요하지 않으면 접근 가능
        }
        return isMember; // 멤버십이 필요하면 멤버 여부에 따라 결정
    }
} 