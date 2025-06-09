package back.vybz.feed_read_service.feed.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RequestScrollFanFeedDto {

    private String lastId;
    private String sortType;
    private int size;
    private String writerUuid;

    @Builder
    public RequestScrollFanFeedDto(String lastId, String sortType, int size, String writerUuid) {
        this.lastId = lastId;
        this.sortType = sortType;
        this.size = size;
        this.writerUuid = writerUuid;
    }
}
