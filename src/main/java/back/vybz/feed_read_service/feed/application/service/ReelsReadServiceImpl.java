package back.vybz.feed_read_service.feed.application.service;

import back.vybz.feed_read_service.common.util.CursorPage;
import back.vybz.feed_read_service.feed.domain.FeedRead;
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
    public ResponseScrollReelsDto getReelsScrollList(RequestScrollReelsDto requestScrollReelsDto) {
        String cursor = requestScrollReelsDto.getLastId();
        String writerUuid = requestScrollReelsDto.getWriterUuid();

        List<FeedRead> reels = reelsReadRepository.findWithScroll(
                requestScrollReelsDto.getSortType(),
                cursor,
                writerUuid,
                requestScrollReelsDto.getSize() + 1
        );

        CursorPage<FeedRead> cursorPage = CursorPage.of(
                reels,
                requestScrollReelsDto.getSize(),
                FeedRead::getId
        );

        return ResponseScrollReelsDto.from(cursorPage);
    }

    @Override
    public ResponseScrollReelsDto getBuskerReelsScrollList(RequestScrollReelsDto requestScrollReelsDto) {
        // 특정 버스커 조회는 getReelsScrollList로 통합
        return getReelsScrollList(requestScrollReelsDto);
    }
}