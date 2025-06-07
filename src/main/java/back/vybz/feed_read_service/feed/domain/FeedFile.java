package back.vybz.feed_read_service.feed.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FeedFile {

    // 파일 이름
    private String fileName;

    // 파일 url
    private String fileUrl;

    //썸네일 url
    private String thumbnailUrl;

    // 파일 타입
    private FileType fileType;

}
