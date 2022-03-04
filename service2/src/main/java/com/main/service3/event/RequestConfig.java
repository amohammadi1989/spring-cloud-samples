package com.main.service3.event;
import com.rabbitmq.client.*;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.AsyncRabbitTemplate;
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
public class RequestConfig {
 
  @Bean
  public Queue queue(){
    return new Queue("Response");
  }
  

  @Bean
  public DirectExchange directExchange(){
    return new DirectExchange( "ExchangePerson" );
  }
  @Bean
  public Binding binding(Queue queue,DirectExchange directExchange){
    return BindingBuilder.bind( queue).to( directExchange ).with( "KeyResponse" );
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
  @Bean
  public AsyncAmqpTemplate asyncAmqpTemplate(RabbitTemplate rabbitTemplate){
    return  new AsyncRabbitTemplate(rabbitTemplate);
  }
}
