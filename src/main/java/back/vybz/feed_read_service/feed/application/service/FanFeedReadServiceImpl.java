package back.vybz.feed_read_service.feed.application.service;

import back.vybz.feed_read_service.common.exception.BaseException;
import back.vybz.feed_read_service.common.exception.BaseResponseStatus;
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
    public ResponseScrollFanFeedDto getFanFeedScrollList(RequestScrollFanFeedDto requestScrollFanFeedDto) {
        List<FanFeedRead> feeds = fanFeedReadRepository.findWithScroll(
                requestScrollFanFeedDto.getSortType(),
                requestScrollFanFeedDto.getLastId(),
                requestScrollFanFeedDto.getWriterUuid(),
                requestScrollFanFeedDto.getSize() + 1
        );

        CursorPage<FanFeedRead> cursorPage = CursorPage.of(
                feeds,
                requestScrollFanFeedDto.getSize(),
                FanFeedRead::getId
        );

        return ResponseScrollFanFeedDto.from(cursorPage);
    }

    @Override
    public ResponseScrollFanFeedDto getUserFanFeedScrollList(RequestScrollFanFeedDto requestScrollFanFeedDto){
        String cursor = requestScrollFanFeedDto.getLastId();

        List<FanFeedRead> feeds = fanFeedReadRepository.findWithScroll(
                requestScrollFanFeedDto.getSortType(),
                cursor,
                requestScrollFanFeedDto.getWriterUuid(),
                requestScrollFanFeedDto.getSize() + 1
        );

        CursorPage<FanFeedRead> cursorPage = CursorPage.of(
                feeds,
                requestScrollFanFeedDto.getSize(),
                FanFeedRead::getId
        );
        return ResponseScrollFanFeedDto.from(cursorPage);
    }






    @Override
    public ResponseFanFeedDto getFanFeedDetail(String fanFeedId) {
        FanFeedRead fanFeed = fanFeedReadRepository.findById(fanFeedId)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.FAN_FEED_NOT_FOUND));
        return ResponseFanFeedDto.from(fanFeed);
    }
}
