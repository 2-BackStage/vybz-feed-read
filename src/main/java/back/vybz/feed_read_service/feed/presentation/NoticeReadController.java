package back.vybz.feed_read_service.feed.presentation;

import back.vybz.feed_read_service.common.entity.BaseResponseEntity;
import back.vybz.feed_read_service.feed.application.service.NoticeReadService;
import back.vybz.feed_read_service.feed.dto.request.RequestScrollNoticeDto;
import back.vybz.feed_read_service.feed.dto.response.ResponseNoticeDto;

import back.vybz.feed_read_service.feed.dto.response.ResponseScrollNoticeDto;

import back.vybz.feed_read_service.feed.vo.response.ResponseNoticeVo;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/feed")
@RequiredArgsConstructor
public class NoticeReadController {

    private final NoticeReadService noticeReadService;

    @Operation(
            summary = "공지 목록 무한스크롤 조회 API",
            description = "공지 데이터를 무한스크롤 방식으로 조회합니다. " +
                    "size는 한 페이지에 가져올 개수이며, 다음 목록 요청 시에는 lastId에 이전 목록의 마지막 id를 넣어주세요. " +
                    "sortType은 정렬 기준입니다. 'LATEST', 'LIKES', 'COMMENTS' 중 하나를 입력해주세요.",
            tags = {"BUSKER-SERVICE"}
    )
    @GetMapping("/notice")
    public BaseResponseEntity<ResponseScrollNoticeDto> getNotices(
            @RequestParam(required = false) String lastId,
            @RequestParam(defaultValue = "LATEST") String sortType,
            @RequestParam(defaultValue = "10") int size
    ) {
        RequestScrollNoticeDto requestDto = RequestScrollNoticeDto.builder()
                .lastId(lastId)
                .sortType(sortType)
                .size(size)
                .build();

        return BaseResponseEntity.ok(noticeReadService.getNoticeScrollList(requestDto));
    }

    @Operation(
            summary = "공지 상세 조회 API",
            description = "버스커 공지의 상세 정보를 조회하는 API입니다.",
            tags = {"BUSKER-SERVICE"}
    )
    @GetMapping("/notice/{noticeId}")
    public BaseResponseEntity<ResponseNoticeVo> getNoticeDetail(@PathVariable String noticeId) {
        ResponseNoticeDto responseNoticeDto = noticeReadService.getNoticeDetail(noticeId);
        return BaseResponseEntity.ok(responseNoticeDto.toVo());
    }
}
