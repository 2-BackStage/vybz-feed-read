package back.vybz.feed_read_service.feed.dto.response;

import back.vybz.feed_read_service.common.util.CursorPage;
import back.vybz.feed_read_service.feed.vo.response.ResponseScrollNoticeVo;
import back.vybz.feed_read_service.feed.domain.NoticeRead;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class ResponseScrollNoticeDto {

    private List<ResponseScrollNoticeVo> content;
    private boolean hasNext;
    private String nextCursor;

    @Builder
    public ResponseScrollNoticeDto(List<ResponseScrollNoticeVo> content,
                                   boolean hasNext,
                                   String nextCursor) {
        this.content = content;
        this.hasNext = hasNext;
        this.nextCursor = nextCursor;
    }

    public static ResponseScrollNoticeDto from(CursorPage<NoticeRead> cursorPage){
        return ResponseScrollNoticeDto.builder()
                .content(ResponseScrollNoticeVo.listFrom(cursorPage.getContent()))
                .hasNext(cursorPage.getHasNext())
                .nextCursor(cursorPage.getNextCursor())
                .build();
    }
}
