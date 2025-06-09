package back.vybz.feed_read_service.feed.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RequestScrollNoticeDto {
    private String lastId;
    private String sortType;
    private int size;

    @Builder
    private RequestScrollNoticeDto(String lastId, String sortType, int size) {
        this.lastId = lastId;
        this.sortType = sortType;
        this.size = size;
    }
}
