package com.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@SpringBootApplication
@EnableDiscoveryClient
@RibbonClient(name = "server1", configuration = RibbonConfig.class)
@EnableFeignClients
@EnableMongoRepositories
@EnableCaching
public class Service1Applications {

	public static void main (String[] args) {
    //org.springframework.cloud.openfeign.FeignClientsConfiguration
	  //feign.codec.
		//feign.optionals.OptionalDecoder
		SpringApplication.run( Service1Applications.class, args);
	}
	@Bean
	@LoadBalanced
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}
}
