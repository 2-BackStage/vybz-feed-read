package back.vybz.feed_read_service.feed.application.service;

import back.vybz.feed_read_service.common.util.CursorPage;
import back.vybz.feed_read_service.feed.domain.NoticeRead;
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

        List<NoticeRead> notices = noticeReadRepository.findWithScroll(
                requestScrollNoticeDto.getSortType(),
                cursor,
                requestScrollNoticeDto.getSize() + 1
        );

        CursorPage<NoticeRead> cursorPage = CursorPage.of(
                notices,
                requestScrollNoticeDto.getSize(),
                NoticeRead::getId
        );

        return ResponseScrollNoticeDto.from(cursorPage);
    }

    @Override
    public ResponseNoticeDto getNoticeDetail(String noticeId) {
        NoticeRead notice = noticeReadRepository.findById(noticeId)
                .orElseThrow(() -> new IllegalArgumentException("공지 정보를 찾을 수 없습니다."));
        return ResponseNoticeDto.from(notice);
    }
}