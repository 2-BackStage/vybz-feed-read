package back.vybz.feed_read_service.kafka.config;

import back.vybz.feed_read_service.kafka.event.NoticeCreateEvent;
import back.vybz.feed_read_service.kafka.event.NoticeUpdateEvent;
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
public class NoticeEventConfig {

    private final CommonKafkaConfig commonKafkaConfig;

    @Bean
    public ConsumerFactory<String, NoticeCreateEvent> noticeCreateConsumerFactory() {
        return new DefaultKafkaConsumerFactory<>(
                commonKafkaConfig.commonConsumerConfigs(),
                new StringDeserializer(),
                new ErrorHandlingDeserializer<>(new JsonDeserializer<>(NoticeCreateEvent.class, false))
        );
    }

    @Bean(name = "noticeCreateKafkaListenerContainerFactory")
    public ConcurrentKafkaListenerContainerFactory<String, NoticeCreateEvent> noticeCreateKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, NoticeCreateEvent> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(noticeCreateConsumerFactory());
        return factory;
    }

    @Bean
    public ConsumerFactory<String, NoticeUpdateEvent> noticeUpdateConsumerFactory() {
        return new DefaultKafkaConsumerFactory<>(
                commonKafkaConfig.commonConsumerConfigs(),
                new StringDeserializer(),
                new ErrorHandlingDeserializer<>(new JsonDeserializer<>(NoticeUpdateEvent.class, false))
        );
    }

    @Bean(name = "noticeUpdateKafkaListenerContainerFactory")
    public ConcurrentKafkaListenerContainerFactory<String, NoticeUpdateEvent> noticeUpdateKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, NoticeUpdateEvent> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(noticeUpdateConsumerFactory());
        return factory;
    }
}

