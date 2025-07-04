package back.vybz.feed_read_service.feed.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RequestScrollReelsDto {

    private String lastId;
    private String writerUuid;
    private String sortType;
    private int size;

    @Builder
    public RequestScrollReelsDto(String lastId,
                                 String writerUuid,
                                 String sortType,
                                 int size) {
        this.lastId = lastId;
        this.writerUuid = writerUuid;
        this.sortType = sortType;
        this.size = size;
    }
}
