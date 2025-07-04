package back.vybz.feed_read_service.feed.application.service;

import back.vybz.feed_read_service.common.exception.BaseException;
import back.vybz.feed_read_service.common.exception.BaseResponseStatus;
import back.vybz.feed_read_service.common.util.CursorPage;
import back.vybz.feed_read_service.feed.domain.FeedRead;
import back.vybz.feed_read_service.feed.dto.request.RequestScrollNoticeDto;
import back.vybz.feed_read_service.feed.dto.response.ResponseNoticeDto;
import back.vybz.feed_read_service.feed.dto.response.ResponseScrollNoticeDto;
import back.vybz.feed_read_service.feed.infrastructure.NoticeReadRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoticeReadServiceImpl implements NoticeReadService {

    private final NoticeReadRepository noticeReadRepository;

    @Override
    public ResponseScrollNoticeDto getNoticeScrollList(RequestScrollNoticeDto requestScrollNoticeDto) {
        String cursor = requestScrollNoticeDto.getLastId();
        String buskerUuid = requestScrollNoticeDto.getBuskerUuid();

        List<FeedRead> notices;
        
        if (buskerUuid != null && !buskerUuid.isBlank()) {
            // 특정 버스커의 공지 조회
            notices = noticeReadRepository.findWithScrollByBusker(
                    requestScrollNoticeDto.getSortType(),
                    cursor,
                    requestScrollNoticeDto.getSize() + 1,
                    buskerUuid
            );
        } else {
            // 모든 공지 조회
            notices = noticeReadRepository.findWithScroll(
                    requestScrollNoticeDto.getSortType(),
                    cursor,
                    requestScrollNoticeDto.getSize() + 1
            );
        }

        CursorPage<FeedRead> cursorPage = CursorPage.of(
                notices,
                requestScrollNoticeDto.getSize(),
                FeedRead::getId
        );

        return ResponseScrollNoticeDto.from(cursorPage);
    }

    @Override
    public ResponseNoticeDto getNoticeDetail(String noticeId) {
        FeedRead notice = noticeReadRepository.findById(noticeId)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NOTICE_NOT_FOUND));
        return ResponseNoticeDto.from(notice);
    }
}