package back.vybz.feed_read_service.feed.infrastructure.feign;

import back.vybz.feed_read_service.common.entity.BaseResponseEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "membership-service")
public interface MembershipFeignClient {

    /**
     * 사용자가 특정 버스커를 구독하고 있는지 확인
     */
    @GetMapping("/api/v1/membership/check/{userUuid}/{buskerUuid}")
    BaseResponseEntity<Boolean> checkSubscription(@PathVariable String userUuid, @PathVariable String buskerUuid);
} 