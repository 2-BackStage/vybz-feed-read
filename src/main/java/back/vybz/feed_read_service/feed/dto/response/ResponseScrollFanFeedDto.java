package back.vybz.feed_read_service.feed.dto.response;

import back.vybz.feed_read_service.common.util.CursorPage;
import back.vybz.feed_read_service.feed.domain.FanFeedRead;
import back.vybz.feed_read_service.feed.vo.response.ResponseScrollFanFeedVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class ResponseScrollFanFeedDto {

    private List<ResponseScrollFanFeedVo> content;
    private boolean hasNext;
    private String nextCursor;

    @Builder
    public ResponseScrollFanFeedDto(List<ResponseScrollFanFeedVo> content,
                                    boolean hasNext,
                                    String nextCursor) {
        this.content = content;
        this.hasNext = hasNext;
        this.nextCursor = nextCursor;
    }

    public static ResponseScrollFanFeedDto from(CursorPage<FanFeedRead> cursorPage) {
        return ResponseScrollFanFeedDto.builder()
                .content(ResponseScrollFanFeedVo.listFrom(cursorPage.getContent()))
                .hasNext(cursorPage.getHasNext())
                .nextCursor(cursorPage.getNextCursor())
                .build();
    }
}
