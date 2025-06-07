package back.vybz.feed_service.feed.dto.response;

import back.vybz.feed_service.feed.domain.mongodb.Feed;
import back.vybz.feed_service.feed.vo.response.ResponseScrollReelsVo;
import back.vybz.feed_service.common.util.CursorPage;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class ResponseScrollReelsDto {

    private List<ResponseScrollReelsVo> content;
    private boolean hasNext;
    private String nextCursor;

    @Builder
    public ResponseScrollReelsDto(List<ResponseScrollReelsVo> content,
                                  boolean hasNext,
                                  String nextCursor) {
        this.content = content;
        this.hasNext = hasNext;
        this.nextCursor = nextCursor;
    }

    public static ResponseScrollReelsDto from(CursorPage<Feed> cursorPage) {
        return ResponseScrollReelsDto.builder()
                .content(ResponseScrollReelsVo.listFrom(cursorPage.getContent()))
                .hasNext(cursorPage.getHasNext())
                .nextCursor(cursorPage.getNextCursor())
                .build();
    }
}
