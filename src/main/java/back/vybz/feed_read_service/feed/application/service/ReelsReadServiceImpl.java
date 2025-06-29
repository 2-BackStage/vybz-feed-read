package back.vybz.feed_read_service.feed.application.service;

import back.vybz.feed_read_service.common.util.CursorPage;
import back.vybz.feed_read_service.feed.domain.FeedRead;
import back.vybz.feed_read_service.feed.dto.request.RequestScrollReelsDto;
import back.vybz.feed_read_service.feed.dto.response.ResponseScrollReelsDto;
import back.vybz.feed_read_service.feed.infrastructure.ReelsReadRepository;
import back.vybz.feed_read_service.feed.infrastructure.feign.MembershipFeignClient;
import back.vybz.feed_read_service.feed.vo.response.ResponseScrollReelsVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReelsReadServiceImpl implements ReelsReadService {

    private final ReelsReadRepository reelsReadRepository;
    private final MembershipFeignClient membershipFeignClient;

    @Override
    public ResponseScrollReelsDto getReelsScrollList(RequestScrollReelsDto requestScrollReelsDto) {
        String cursor = requestScrollReelsDto.getLastId();
        String userUuid = requestScrollReelsDto.getUserUuid();

        List<FeedRead> reels = reelsReadRepository.findWithScroll(
                requestScrollReelsDto.getSortType(),
                cursor,
                null,
                requestScrollReelsDto.getSize() + 1
        );

        // 멤버십 체크가 필요한 릴스만 필터링
        List<FeedRead> accessibleReels = reels.stream()
                .filter(reel -> {
                    if (!reel.isMemberShipRequired()) {
                        return true; // 멤버십이 필요하지 않으면 접근 가능
                    }
                    
                    // 멤버십이 필요한 경우 구독 여부 확인
                    try {
                        Boolean isSubscribed = membershipFeignClient.checkSubscription(userUuid, reel.getWriterUuid()).result();
                        return Boolean.TRUE.equals(isSubscribed);
                    } catch (Exception e) {
                        // FeignClient 호출 실패 시 접근 거부
                        return false;
                    }
                })
                .collect(Collectors.toList());

        CursorPage<FeedRead> cursorPage = CursorPage.of(
                accessibleReels,
                requestScrollReelsDto.getSize(),
                FeedRead::getId
        );

        return ResponseScrollReelsDto.builder()
                .content(ResponseScrollReelsVo.listFrom(cursorPage.getContent()))
                .hasNext(cursorPage.getHasNext())
                .nextCursor(cursorPage.getNextCursor())
                .build();
    }

    @Override
    public ResponseScrollReelsDto getBuskerReelsScrollList(RequestScrollReelsDto requestScrollReelsDto) {
        String cursor = requestScrollReelsDto.getLastId();
        String userUuid = requestScrollReelsDto.getUserUuid();

        List<FeedRead> reels = reelsReadRepository.findWithScroll(
                requestScrollReelsDto.getSortType(),
                cursor,
                requestScrollReelsDto.getWriterUuid(),
                requestScrollReelsDto.getSize() + 1
        );

        // 멤버십 체크가 필요한 릴스만 필터링
        List<FeedRead> accessibleReels = reels.stream()
                .filter(reel -> {
                    if (!reel.isMemberShipRequired()) {
                        return true; // 멤버십이 필요하지 않으면 접근 가능
                    }
                    
                    // 멤버십이 필요한 경우 구독 여부 확인
                    try {
                        Boolean isSubscribed = membershipFeignClient.checkSubscription(userUuid, reel.getWriterUuid()).result();
                        return Boolean.TRUE.equals(isSubscribed);
                    } catch (Exception e) {
                        // FeignClient 호출 실패 시 접근 거부
                        return false;
                    }
                })
                .collect(Collectors.toList());

        CursorPage<FeedRead> cursorPage = CursorPage.of(
                accessibleReels,
                requestScrollReelsDto.getSize(),
                FeedRead::getId
        );

        return ResponseScrollReelsDto.builder()
                .content(ResponseScrollReelsVo.listFrom(cursorPage.getContent()))
                .hasNext(cursorPage.getHasNext())
                .nextCursor(cursorPage.getNextCursor())
                .build();
    }
}