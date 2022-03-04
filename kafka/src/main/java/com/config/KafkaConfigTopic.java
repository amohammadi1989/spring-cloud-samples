package com.config;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.HashMap;
import java.util.Map;
/**
 * Created By: Ali Mohammadi
 * Date: 22 Dec, 2021
 */
@Configuration
public class KafkaConfigTopic {
  private String bootstrapAddress;
  
  @Bean
  public KafkaAdmin kafkaAdmin(){
    Map<String,Object> config=new HashMap<>();
    config.put( AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:29092" );
    return new KafkaAdmin( config );
  }
  @Bean
  public NewTopic topic1(){
    return new NewTopic( "Person",2,(short) 1 );
  }
  
}
