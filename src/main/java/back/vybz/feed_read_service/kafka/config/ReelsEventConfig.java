package back.vybz.feed_read_service.kafka.config;

import back.vybz.feed_read_service.kafka.event.ReelsCreateEvent;
import back.vybz.feed_read_service.kafka.event.ReelsUpdateEvent;
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
public class ReelsEventConfig {

    private final CommonKafkaConfig commonKafkaConfig;

    @Bean
    public ConsumerFactory<String, ReelsCreateEvent> reelsCreateConsumerFactory() {
        return new DefaultKafkaConsumerFactory<>(
                commonKafkaConfig.commonConsumerConfigs(),
                new StringDeserializer(),
                new ErrorHandlingDeserializer<>(new JsonDeserializer<>(ReelsCreateEvent.class, false))
        );
    }

    @Bean(name = "reelsCreateKafkaListenerContainerFactory")
    public ConcurrentKafkaListenerContainerFactory<String, ReelsCreateEvent> reelsCreateKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, ReelsCreateEvent> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(reelsCreateConsumerFactory());
        return factory;
    }

    @Bean
    public ConsumerFactory<String, ReelsUpdateEvent> reelsUpdateConsumerFactory() {
        return new DefaultKafkaConsumerFactory<>(
                commonKafkaConfig.commonConsumerConfigs(),
                new StringDeserializer(),
                new ErrorHandlingDeserializer<>(new JsonDeserializer<>(ReelsUpdateEvent.class, false))
        );
    }

    @Bean(name = "reelsUpdateKafkaListenerContainerFactory")
    public ConcurrentKafkaListenerContainerFactory<String, ReelsUpdateEvent> reelsUpdateKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, ReelsUpdateEvent> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(reelsUpdateConsumerFactory());
        return factory;
    }
}
