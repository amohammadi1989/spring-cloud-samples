package com.config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * Created By: Ali Mohammadi
 * Date: 22 Dec, 2021
 */
@RestController
@RequestMapping("/rest")
public class KafkaController {
  KafkaTemplate<String,String> kafkaTemplate;
  
  public KafkaController(KafkaTemplate<String, String> kafkaTemplate) {
    this.kafkaTemplate = kafkaTemplate;
  }
  
  @GetMapping("/v1")
  public void test1(){
    kafkaTemplate.send( "Person","This is test" );
    System.out.println("Message send");
  }
  @KafkaListener(topics = "Person",groupId = "p1")
  public void test(String msg){
    System.out.println("Msg is equals :"+msg );
  }
}
