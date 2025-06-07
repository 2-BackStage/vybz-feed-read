package back.vybz.feed_read_service.feed.vo.response;

import back.vybz.feed_read_service.feed.domain.FeedFile;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;

@Getter
@NoArgsConstructor
public class ResponseAboutVo {
    private String id;
    private String content;
    private List<String> hashTag;
    private List<FeedFile> fileList;
    private Instant createdAt;

    @Builder
    public ResponseAboutVo(String id,
                           String content,
                           List<String> hashTag,
                           List<FeedFile> fileList,
                           Instant createdAt) {
        this.id = id;
        this.content = content;
        this.hashTag = hashTag;
        this.fileList = fileList;
        this.createdAt = createdAt;
    }
}
