package com.main.config;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;

import java.time.Duration;

import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
/**
 * Created By: Ali Mohammadi
 * Date: 26 Oct, 2021
 */
@Configuration
public class AppConfig {
  @Bean
  public ModelMapper modelMapper(){
    return new ModelMapper();
  }
  
  @Bean
  public RedisCacheConfiguration cacheConfiguration() {
    return RedisCacheConfiguration.defaultCacheConfig()
    .entryTtl( Duration.ofMinutes( 2))
    .disableCachingNullValues()
    .serializeValuesWith( RedisSerializationContext.SerializationPair.fromSerializer( new GenericJackson2JsonRedisSerializer()));
  }
}
