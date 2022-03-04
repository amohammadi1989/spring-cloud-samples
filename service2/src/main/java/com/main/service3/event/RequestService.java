package com.main.service3.event;
import com.main.person.RequestEvent;
import com.main.person.ResponseEvent;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.UUID;
/**
 * Created By: Ali Mohammadi
 * Date: 04 Nov, 2021
 */
@Service
public class RequestService {
  
  @Value( "${test.rabbitmq.queue}" )
  String queueName;
  @Value( "${test.rabbitmq.exchange}" )
  String exchange;
  @Value( "${test.rabbitmq.routingkey}" )
  String routingkey;
  @Value( "${test1.rabbitmq.routingkey}" )
  String routingkey1;
  
  @Autowired
  private DirectExchange directExchange;
  
  @Autowired
  AmqpTemplate amqpTemplate;
  @Autowired
  AsyncAmqpTemplate asyncAmqpTemplate;
  
  
  public void send(RequestEvent requestEvent){
    amqpTemplate.convertAndSend( exchange,routingkey,requestEvent );
    System.out.println("Send messages successfully.");
  }
  
  public void sendRequestAndReply(RequestEvent requestEvent){
    UUID correlationId = UUID.randomUUID();
    
    Object responseEvent=
    amqpTemplate.convertSendAndReceive("ExchangePerson","KeyRequest", requestEvent );
    System.out.println(responseEvent.toString());
  }
  
  public void requestAndReplyAsync(RequestEvent requestEvent){
  
    ListenableFuture<ResponseEvent> response=asyncAmqpTemplate.convertSendAndReceive(
    "ExchangePerson", "KeyRequest", requestEvent);
  
    System.out.println("test..................async");
   
    try {
      ResponseEvent  res= response.get();
      System.out.println(res.toString());
    } catch (Exception e) {
     e.printStackTrace();
    }
  
  
  }
  public void sendAndForget(RequestEvent requestEvent) throws RuntimeException{
  UUID uuid=UUID.randomUUID();
    MessagePostProcessor messagePostProcessor = message -> {
      MessageProperties messageProperties = message.getMessageProperties();
      messageProperties.setReplyTo("Response");
      messageProperties.setCorrelationId(uuid.toString());
      return message;
    };
    amqpTemplate.convertAndSend( "ExchangePerson","KeyRequest",requestEvent,messagePostProcessor );
    
  }
  @RabbitListener(queues = "Response")
  public void getResponse(Object responseEvent){
    System.out.println(responseEvent.toString());
  }
}
