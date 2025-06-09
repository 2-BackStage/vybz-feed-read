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
            summary = "전체 릴스 무한스크롤 조회 API",
            description = "전체 릴스를 무한스크롤 방식으로 조회합니다.\n" +
                    "- `lastId` : 마지막 릴스 ID (null이면 처음부터)\n" +
                    "- `sortType` : 정렬 기준 [LATEST, LIKES, COMMENTS]\n" +
                    "- `size` : 한 페이지에 가져올 개수",
            tags = {"REELS-READ"}
    )
    @GetMapping("/reels")
    public BaseResponseEntity<ResponseScrollReelsDto> getReels(
            @RequestParam(required = false) String lastId,
            @RequestParam(defaultValue = "LATEST") String sortType,
            @RequestParam(defaultValue = "10") int size
    ) {
        RequestScrollReelsDto request = RequestScrollReelsDto.builder()
                .lastId(lastId)
                .sortType(sortType)
                .size(size)
                .build();

        return new BaseResponseEntity<>(reelsReadService.getReelsScrollList(request));
    }

    @Operation(
            summary = "버스커 릴스 무한스크롤 조회 API",
            description = "특정 버스커의 릴스를 무한스크롤 방식으로 조회합니다.\n" +
                    "- `writerUuid` : 버스커 UUID\n" +
                    "- `lastId` : 마지막 릴스 ID (null이면 처음부터)\n" +
                    "- `sortType` : 정렬 기준 [LATEST, LIKES, COMMENTS]\n" +
                    "- `size` : 한 페이지에 가져올 개수",
            tags = {"REELS-READ"}
    )
    @GetMapping("/reels/{writerUuid}")
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

        return new BaseResponseEntity<>(reelsReadService.getBuskerReelsScrollList(request));
    }
}
