package com.main.events;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.Date;
/**
 * Created By: Ali Mohammadi
 * Date: 06 Nov, 2021
 */
@Service
public class ResponseService {
  
/*  @RabbitListener(queues = "test.person.queue")
  public void getMessage(RequestEvent requestEvent){
    System.out.println(requestEvent.toString());
  }*/
  
/*  @RabbitListener(queues = "Request")
  public ResponseEvent getRequestFromService2(RequestEvent requestEvent){
    System.out.println(requestEvent);
  
    try {
       Thread.sleep( 10000 );
    } catch (Exception e) {
    e.printStackTrace();
    } finally {
    
    }
  
    return ResponseEvent.builder().msg( requestEvent.getMsg()+" response" )
    .date( new Date().toString() ).build();
  }*/
}
