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
    private String buskerUuid;

    @Builder
    private RequestScrollNoticeDto(String lastId, String sortType, int size, String buskerUuid) {
        this.lastId = lastId;
        this.sortType = sortType;
        this.size = size;
        this.buskerUuid = buskerUuid;
    }
}
