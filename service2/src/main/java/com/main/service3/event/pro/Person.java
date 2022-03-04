package com.main.service3.event.pro;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;
/**
 * Created By: Ali Mohammadi
 * Date: 04 Nov, 2021
 */
public interface Person {
  @Input
  SubscribableChannel myInput();
  
  @Output
  MessageChannel myOutput();
  
}
