package back.vybz.feed_read_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class FeedReedServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(FeedReedServiceApplication.class, args);
    }

}
