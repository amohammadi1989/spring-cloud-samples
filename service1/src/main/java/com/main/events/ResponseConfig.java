package com.main.events;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/**
 * Created By: Ali Mohammadi
 * Date: 06 Nov, 2021
 */
@Configuration
public class ResponseConfig {

  

  @Bean
  public Queue queue(){
    return new Queue("Request");
  }
  
  @Bean
  public DirectExchange directExchange(){
    return new DirectExchange( "ExchangePerson" );
  }
  @Bean
  public Binding binding(Queue queue,DirectExchange directExchange){
    return BindingBuilder.bind( queue).to( directExchange ).with( "KeyRequest" );
  }
 
  @Bean
  public MessageConverter jsonMessageConverter() {
    return new Jackson2JsonMessageConverter();
  }
  
  
  @Bean
  public AmqpTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
    final RabbitTemplate rabbitTemplate = new RabbitTemplate( connectionFactory);
    rabbitTemplate.setMessageConverter(jsonMessageConverter());
    return rabbitTemplate;
  }
}
