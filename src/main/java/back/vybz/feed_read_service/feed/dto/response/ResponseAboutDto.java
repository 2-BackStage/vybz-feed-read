package back.vybz.feed_read_service.feed.dto.response;

import back.vybz.feed_read_service.feed.domain.AboutRead;
import back.vybz.feed_read_service.feed.domain.FeedFile;
import back.vybz.feed_read_service.feed.vo.response.ResponseAboutVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;

@Getter
@NoArgsConstructor
public class ResponseAboutDto {
    private String id;
    private String content;
    private List<String> hashTag;
    private List<FeedFile> fileList;
    private Instant createdAt;

    @Builder
    public ResponseAboutDto(String id,
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

    public static ResponseAboutDto from(AboutRead aboutRead) {
        return ResponseAboutDto.builder()
                .id(aboutRead.getId())
                .content(aboutRead.getContent())
                .hashTag(aboutRead.getHashTag())
                .fileList(aboutRead.getFileList())
                .createdAt(aboutRead.getCreatedAt())
                .build();
    }

    public ResponseAboutVo toVo(){
        return ResponseAboutVo.builder()
                .id(id)
                .content(content)
                .hashTag(hashTag)
                .fileList(fileList)
                .createdAt(createdAt)
                .build();
    }
}

