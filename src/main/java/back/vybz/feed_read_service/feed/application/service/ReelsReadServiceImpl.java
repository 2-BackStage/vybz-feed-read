package back.vybz.feed_read_service.feed.application.service;

import back.vybz.feed_read_service.common.util.CursorPage;
import back.vybz.feed_read_service.feed.domain.ReelsRead;
import back.vybz.feed_read_service.feed.dto.request.RequestScrollReelsDto;
import back.vybz.feed_read_service.feed.dto.response.ResponseScrollReelsDto;
import back.vybz.feed_read_service.feed.infrastructure.ReelsReadRepository;
import back.vybz.feed_read_service.feed.vo.response.ResponseScrollReelsVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReelsReadServiceImpl implements ReelsReadService {

    private final ReelsReadRepository reelsReadRepository;

    @Override
    public ResponseScrollReelsDto getReelsScrollList(RequestScrollReelsDto request) {
        String cursor = request.getLastId();

        List<ReelsRead> reels = reelsReadRepository.findWithScroll(
                request.getSortType(),
                cursor,
                null,
                request.getSize() + 1
        );

        CursorPage<ReelsRead> cursorPage = CursorPage.of(
                reels,
                request.getSize(),
                ReelsRead::getId
        );

        return ResponseScrollReelsDto.builder()
                .content(ResponseScrollReelsVo.listFrom(cursorPage.getContent()))
                .hasNext(cursorPage.getHasNext())
                .nextCursor(cursorPage.getNextCursor())
                .build();
    }

    @Override
    public ResponseScrollReelsDto getBuskerReelsScrollList(RequestScrollReelsDto request) {
        String cursor = request.getLastId();

        List<ReelsRead> reels = reelsReadRepository.findWithScroll(
                request.getSortType(),
                cursor,
                request.getWriterUuid(),
                request.getSize() + 1
        );

        CursorPage<ReelsRead> cursorPage = CursorPage.of(
                reels,
                request.getSize(),
                ReelsRead::getId
        );

        return ResponseScrollReelsDto.builder()
                .content(ResponseScrollReelsVo.listFrom(cursorPage.getContent()))
                .hasNext(cursorPage.getHasNext())
                .nextCursor(cursorPage.getNextCursor())
                .build();
    }
}