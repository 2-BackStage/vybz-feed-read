package back.vybz.feed_read_service.kafka.config;

import back.vybz.feed_read_service.kafka.event.CommentCountEvent;
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
public class CommentCountEventConfig {

    private final CommonKafkaConfig commonKafkaConfig;

    @Bean
    public ConsumerFactory<String, CommentCountEvent> commentCountEventConsumerFactory(){
        return new DefaultKafkaConsumerFactory<>(
                commonKafkaConfig.commonConsumerConfigs(),
                new StringDeserializer(),
                new ErrorHandlingDeserializer<>(new JsonDeserializer<>(CommentCountEvent.class, false))
        );
    }

    @Bean(name = "commentCountKafkaListenerContainerFactory")
    public ConcurrentKafkaListenerContainerFactory<String, CommentCountEvent> commentCountKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, CommentCountEvent> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(commentCountEventConsumerFactory());
        return factory;
    }
}
