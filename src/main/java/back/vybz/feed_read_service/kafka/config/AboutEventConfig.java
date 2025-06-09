package back.vybz.feed_read_service.kafka.config;

import back.vybz.feed_read_service.kafka.event.AboutCreateEvent;
import back.vybz.feed_read_service.kafka.event.AboutUpdateEvent;
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
public class AboutEventConfig {

    private final CommonKafkaConfig commonKafkaConfig;

    @Bean
    public ConsumerFactory<String, AboutCreateEvent> aboutCreateConsumerFactory() {
        return new DefaultKafkaConsumerFactory<>(
                commonKafkaConfig.commonConsumerConfigs(),
                new StringDeserializer(),
                new ErrorHandlingDeserializer<>(new JsonDeserializer<>(AboutCreateEvent.class, false))
        );
    }

    @Bean(name = "aboutCreateKafkaListenerContainerFactory")
    public ConcurrentKafkaListenerContainerFactory<String, AboutCreateEvent> aboutCreateKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, AboutCreateEvent> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(aboutCreateConsumerFactory());
        return factory;
    }

    @Bean
    public ConsumerFactory<String, AboutUpdateEvent> aboutUpdateConsumerFactory() {
        return new DefaultKafkaConsumerFactory<>(
                commonKafkaConfig.commonConsumerConfigs(),
                new StringDeserializer(),
                new ErrorHandlingDeserializer<>(new JsonDeserializer<>(AboutUpdateEvent.class, false))
        );
    }

    @Bean(name = "aboutUpdateKafkaListenerContainerFactory")
    public ConcurrentKafkaListenerContainerFactory<String, AboutUpdateEvent> aboutUpdateKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, AboutUpdateEvent> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(aboutUpdateConsumerFactory());
        return factory;
    }
}
