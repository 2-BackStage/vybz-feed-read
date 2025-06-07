package back.vybz.feed_read_service.feed.presentation;

import back.vybz.feed_read_service.common.entity.BaseResponseEntity;
import back.vybz.feed_read_service.feed.application.service.FanFeedReadService;
import back.vybz.feed_read_service.feed.dto.request.RequestScrollFanFeedDto;
import back.vybz.feed_read_service.feed.dto.response.ResponseFanFeedDto;
import back.vybz.feed_read_service.feed.dto.response.ResponseScrollFanFeedDto;
import back.vybz.feed_read_service.feed.vo.response.ResponseFanFeedVo;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/read/feed")
@RequiredArgsConstructor
public class FanFeedReadController {

    private final FanFeedReadService fanFeedReadService;

    @Operation(
            summary = "팬피드 목록 무한스크롤 조회 API",
            description = "팬피드 데이터를 무한스크롤 방식으로 조회합니다. " +
                    "size는 한 페이지에 가져올 개수이며, 다음 목록 요청 시에는 lastId에 이전 목록의 마지막 id를 넣어주세요. " +
                    "sortType은 정렬 기준입니다. 'LATEST', 'LIKES', 'COMMENTS' 중 하나를 입력해주세요. " +
                    "writerUuid가 있으면 해당 작성자의 피드만 필터링합니다.",
            tags = {"FAN-FEED-READ-SERVICE"}
    )
    @GetMapping("/fan")
    public BaseResponseEntity<ResponseScrollFanFeedDto> getFanFeeds(
            @RequestParam(required = false) String lastId,
            @RequestParam(defaultValue = "LATEST") String sortType,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String writerUuid
    ) {
        RequestScrollFanFeedDto requestDto = RequestScrollFanFeedDto.builder()
                .lastId(lastId)
                .sortType(sortType)
                .size(size)
                .writerUuid(writerUuid)
                .build();

        return BaseResponseEntity.ok(fanFeedReadService.getFanFeedScrollList(requestDto));
    }

    @Operation(
            summary = "팬피드 상세 조회 API",
            description = "팬피드 상세 정보를 조회하는 API입니다.",
            tags = {"FAN-FEED-READ-SERVICE"}
    )
    @GetMapping("/fan/{fanFeedId}")
    public BaseResponseEntity<ResponseFanFeedVo> getFanFeedDetail(@PathVariable String fanFeedId) {
        ResponseFanFeedDto responseDto = fanFeedReadService.getFanFeedDetail(fanFeedId);
        return BaseResponseEntity.ok(responseDto.toVo());
    }
}
