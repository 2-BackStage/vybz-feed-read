package back.vybz.feed_read_service.common.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = "back.vybz.feed_read_service.feed.infrastructure.feign")
public class FeignConfig {
} 