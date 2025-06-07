package back.vybz.feed_read_service.feed.application.service;

import back.vybz.feed_read_service.common.util.CursorPage;
import back.vybz.feed_read_service.feed.domain.FanFeedRead;
import back.vybz.feed_read_service.feed.dto.request.RequestScrollFanFeedDto;
import back.vybz.feed_read_service.feed.dto.response.ResponseFanFeedDto;
import back.vybz.feed_read_service.feed.dto.response.ResponseScrollFanFeedDto;
import back.vybz.feed_read_service.feed.infrastructure.FanFeedReadRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FanFeedReadServiceImpl implements FanFeedReadService {

    private final FanFeedReadRepository fanFeedReadRepository;

    @Override
    public ResponseScrollFanFeedDto getFanFeedScrollList(RequestScrollFanFeedDto request) {
        List<FanFeedRead> feeds = fanFeedReadRepository.findWithScroll(
                request.getSortType(),
                request.getLastId(),
                request.getWriterUuid(),
                request.getSize() + 1
        );

        CursorPage<FanFeedRead> cursorPage = CursorPage.of(
                feeds,
                request.getSize(),
                FanFeedRead::getId
        );

        return ResponseScrollFanFeedDto.from(cursorPage);
    }

    @Override
    public ResponseFanFeedDto getFanFeedDetail(String fanFeedId) {
        FanFeedRead fanFeed = fanFeedReadRepository.findById(fanFeedId)
                .orElseThrow(() -> new IllegalArgumentException("팬피드 정보를 찾을 수 없습니다."));
        return ResponseFanFeedDto.from(fanFeed);
    }
}
