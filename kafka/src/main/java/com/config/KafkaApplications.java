package com.config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;
/**
 * Created By: Ali Mohammadi
 * Date: 22 Dec, 2021
 */
@SpringBootApplication
public class KafkaApplications {
  
  public static void main(String[] args) {
  
    try {
      SpringApplication.run( KafkaApplications.class, args);
    } catch (Exception e) {
    
    }
  
  
  }
}
