package back.vybz.feed_read_service.kafka.config;

import back.vybz.feed_read_service.kafka.event.FeedDeleteEvent;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

@Configuration
@RequiredArgsConstructor
public class FeedDeleteEventConfig {

    private final CommonKafkaConfig commonKafkaConfig;

    @Bean
    public ConsumerFactory<String, FeedDeleteEvent> feedDeleteConsumerFactory() {
        return new DefaultKafkaConsumerFactory<>(
                commonKafkaConfig.commonConsumerConfigs(),
                new StringDeserializer(),
                new ErrorHandlingDeserializer<>(new JsonDeserializer<>(FeedDeleteEvent.class, false))
        );
    }

    @Bean(name = "feedDeleteKafkaListenerContainerFactory")
    public ConcurrentKafkaListenerContainerFactory<String, FeedDeleteEvent> feedDeleteKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, FeedDeleteEvent> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(feedDeleteConsumerFactory());
        return factory;
    }
}
