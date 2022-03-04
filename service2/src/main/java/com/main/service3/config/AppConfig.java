package com.main.service3.config;
import lombok.Builder;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/**
 * Created By: Ali Mohammadi
 * Date: 26 Oct, 2021
 */
@Configuration
public class AppConfig {
  @Bean
  public ModelMapper  modelMapper(){
    return new ModelMapper();
  }
}
