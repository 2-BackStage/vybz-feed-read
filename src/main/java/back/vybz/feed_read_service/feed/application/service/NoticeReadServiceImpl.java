package back.vybz.feed_read_service.feed.application.service;

import back.vybz.feed_read_service.common.exception.BaseException;
import back.vybz.feed_read_service.common.exception.BaseResponseStatus;
import back.vybz.feed_read_service.common.util.CursorPage;
import back.vybz.feed_read_service.feed.domain.FeedRead;
import back.vybz.feed_read_service.feed.dto.request.RequestScrollNoticeDto;
import back.vybz.feed_read_service.feed.dto.response.ResponseNoticeDto;
import back.vybz.feed_read_service.feed.dto.response.ResponseScrollNoticeDto;
import back.vybz.feed_read_service.feed.infrastructure.NoticeReadRepository;
import back.vybz.feed_read_service.feed.infrastructure.feign.MembershipFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NoticeReadServiceImpl implements NoticeReadService {

    private final NoticeReadRepository noticeReadRepository;
    private final MembershipFeignClient membershipFeignClient;

    @Override
    public ResponseScrollNoticeDto getNoticeScrollList(RequestScrollNoticeDto requestScrollNoticeDto) {
        String cursor = requestScrollNoticeDto.getLastId();
        String userUuid = requestScrollNoticeDto.getUserUuid(); // 사용자 UUID 추가 필요

        List<FeedRead> notices = noticeReadRepository.findWithScroll(
                requestScrollNoticeDto.getSortType(),
                cursor,
                requestScrollNoticeDto.getSize() + 1
        );

        // 멤버십 체크가 필요한 공지사항만 필터링
        List<FeedRead> accessibleNotices = notices.stream()
                .filter(notice -> {
                    if (!notice.isMemberShipRequired()) {
                        return true; // 멤버십이 필요하지 않으면 접근 가능
                    }
                    
                    // 멤버십이 필요한 경우 구독 여부 확인
                    try {
                        Boolean isSubscribed = membershipFeignClient.checkSubscription(userUuid, notice.getWriterUuid()).result();
                        return Boolean.TRUE.equals(isSubscribed);
                    } catch (Exception e) {
                        // FeignClient 호출 실패 시 접근 거부
                        return false;
                    }
                })
                .collect(Collectors.toList());

        CursorPage<FeedRead> cursorPage = CursorPage.of(
                accessibleNotices,
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