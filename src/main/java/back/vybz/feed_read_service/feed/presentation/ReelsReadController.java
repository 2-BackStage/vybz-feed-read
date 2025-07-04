package back.vybz.feed_read_service.feed.presentation;

import back.vybz.feed_read_service.common.entity.BaseResponseEntity;
import back.vybz.feed_read_service.feed.application.service.ReelsReadService;
import back.vybz.feed_read_service.feed.dto.request.RequestScrollReelsDto;
import back.vybz.feed_read_service.feed.dto.response.ResponseScrollReelsDto;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/read/feed")
@RequiredArgsConstructor
public class ReelsReadController {

    private final ReelsReadService reelsReadService;

    @Operation(
            summary = "릴스 목록 무한스크롤 조회 API",
            description = "릴스 데이터를 무한스크롤 방식으로 조회합니다. " +
                    "size는 한 페이지에 가져올 개수이며, 다음 목록 요청 시에는 lastId에 이전 목록의 마지막 id를 넣어주세요. " +
                    "sortType은 정렬 기준입니다. 'LATEST', 'LIKES', 'COMMENTS' 중 하나를 입력해주세요. " +
                    "writerUuid를 입력하면 특정 버스커의 릴스만 조회합니다.",
            tags = {"REELS-READ"}
    )
    @GetMapping("/reels")
    public BaseResponseEntity<ResponseScrollReelsDto> getReels(
            @RequestParam(required = false) String lastId,
            @RequestParam(defaultValue = "LATEST") String sortType,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String writerUuid
    ) {
        RequestScrollReelsDto request = RequestScrollReelsDto.builder()
                .lastId(lastId)
                .sortType(sortType)
                .size(size)
                .writerUuid(writerUuid)
                .build();

        return BaseResponseEntity.ok(reelsReadService.getReelsScrollList(request));
    }

    @Operation(
            summary = "특정 버스커 릴스 무한스크롤 조회 API",
            description = "특정 버스커의 릴스를 무한스크롤 방식으로 조회합니다. " +
                    "size는 한 페이지에 가져올 개수이며, 다음 목록 요청 시에는 lastId에 이전 목록의 마지막 id를 넣어주세요. " +
                    "sortType은 정렬 기준입니다. 'LATEST', 'LIKES', 'COMMENTS' 중 하나를 입력해주세요.",
            tags = {"REELS-READ"}
    )
    @GetMapping("/reels/busker/{writerUuid}")
    public BaseResponseEntity<ResponseScrollReelsDto> getBuskerReels(
            @PathVariable String writerUuid,
            @RequestParam(required = false) String lastId,
            @RequestParam(defaultValue = "LATEST") String sortType,
            @RequestParam(defaultValue = "10") int size
    ) {
        RequestScrollReelsDto request = RequestScrollReelsDto.builder()
                .writerUuid(writerUuid)
                .lastId(lastId)
                .sortType(sortType)
                .size(size)
                .build();

        return BaseResponseEntity.ok(reelsReadService.getBuskerReelsScrollList(request));
    }
}
