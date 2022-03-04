package com.main.apigateway;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;

import org.springframework.cloud.circuitbreaker.resilience4j.ReactiveResilience4JCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.beans.Customizer;
import java.time.Duration;
/**
 * Created By: Ali Mohammadi
 * Date: 18 Oct, 2021
 */
@Configuration
public class AppConfig {
  @Bean
  public ReactiveResilience4JCircuitBreakerFactory defaultCustomizer() {
    ReactiveResilience4JCircuitBreakerFactory factory =
    new ReactiveResilience4JCircuitBreakerFactory();
    
    factory.configureDefault(id -> new Resilience4JConfigBuilder( id)
    .circuitBreakerConfig( CircuitBreakerConfig.custom()
                           .slidingWindowSize(5)
                           .permittedNumberOfCallsInHalfOpenState(3)
                           .failureRateThreshold(50.0F)
                           .writableStackTraceEnabled(true)
                           .waitDurationInOpenState( Duration.ofMillis( 1))
                           .build())
    .timeLimiterConfig( TimeLimiterConfig.custom().timeoutDuration( Duration.ofMillis( 200)).build()).build());
    return factory;
  }
  /*@Bean
  public MeterRegistryCustomizer meterRegistryCustomizer(MeterRegistry meterRegistry) {
    return meterRegistry1 -> {
      meterRegistry.config()
      .commonTags("application", "micrometer-youtube-example");
    };
  }*/
}
