package back.vybz.feed_read_service.kafka.config;

import back.vybz.feed_read_service.kafka.event.FanFeedCreateEvent;
import back.vybz.feed_read_service.kafka.event.FanFeedUpdateEvent;
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
public class FanFeedEventConfig {

    private final CommonKafkaConfig commonKafkaConfig;

    @Bean
    public ConsumerFactory<String, FanFeedCreateEvent> fanFeedCreateConsumerFactory() {
        return new DefaultKafkaConsumerFactory<>(
                commonKafkaConfig.commonConsumerConfigs(),
                new StringDeserializer(),
                new ErrorHandlingDeserializer<>(new JsonDeserializer<>(FanFeedCreateEvent.class, false))
        );
    }

    @Bean(name = "fanFeedCreateKafkaListenerContainerFactory")
    public ConcurrentKafkaListenerContainerFactory<String, FanFeedCreateEvent> fanFeedCreateKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, FanFeedCreateEvent> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(fanFeedCreateConsumerFactory());
        return factory;
    }

    @Bean
    public ConsumerFactory<String, FanFeedUpdateEvent> fanFeedUpdateConsumerFactory() {
        return new DefaultKafkaConsumerFactory<>(
                commonKafkaConfig.commonConsumerConfigs(),
                new StringDeserializer(),
                new ErrorHandlingDeserializer<>(new JsonDeserializer<>(FanFeedUpdateEvent.class, false))
        );
    }

    @Bean(name = "fanFeedUpdateKafkaListenerContainerFactory")
    public ConcurrentKafkaListenerContainerFactory<String, FanFeedUpdateEvent> fanFeedUpdateKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, FanFeedUpdateEvent> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(fanFeedUpdateConsumerFactory());
        return factory;
    }
}
