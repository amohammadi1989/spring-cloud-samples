package com.config;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import java.util.HashMap;
import java.util.Map;
/**
 * Created By: Ali Mohammadi
 * Date: 22 Dec, 2021
 */
@EnableKafka
@Configuration
public class KafkaConsumerConfig {
  
  @Bean
  public ConsumerFactory<String, String> consumerFactory() {
    Map<String, Object> props = new HashMap<>();
    props.put(
    ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
    "localhost:29092");
    props.put(
    ConsumerConfig.GROUP_ID_CONFIG,
    "p1");
    props.put(
    ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
    StringDeserializer.class);
    props.put(
    ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
    StringDeserializer.class);
    return new DefaultKafkaConsumerFactory<>( props);
  }
  
  @Bean
  public ConcurrentKafkaListenerContainerFactory<String, String>
  kafkaListenerContainerFactory() {
    
    ConcurrentKafkaListenerContainerFactory<String, String> factory =
    new ConcurrentKafkaListenerContainerFactory<>();
    factory.setConsumerFactory(consumerFactory());
    return factory;
  }
}
