package com.main.service3.ws;
import com.ibm.icu.util.ULocale;
import com.main.service3.dto.Service2Dto;
import com.main.service3.event.RequestService;
import com.main.service3.services.Service2Impl;
import com.main.person.RequestEvent;
import com.main.service3.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

import com.ibm.icu.text.DateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import com.ibm.icu.util.Calendar;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/**
 * Created By: Ali Mohammadi
 * Date: 17 Oct, 2021
 */
@RestController
@RequestMapping("/service2")
public class Service2Controller {
  
  @Autowired
  Environment environment;
  @Autowired
  Service2Impl service2Impl;
  @Autowired
  RequestService personService;
  
  @GetMapping("/getInfo")
  public String getInfo(){
    String serverPort = environment.getProperty("local.server.port");
    String str=LocalDateTime.now().toLocalTime() +"==> Now ,call service2 instance 1"+serverPort;
    System.out.println( str);
    return str;
  }
  
  @GetMapping("/save")
  public String save(){
    service2Impl.saveTransaction( Service2Dto.builder().name( "service2" ).num( 3.2 ).build() );
   return "Request to be submit successfully.";
  }
  
  @GetMapping(value = "/find/{name}",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
  public Flux<UserDto> findAllUser(@PathVariable("name") String name){
    return service2Impl.getAllUsers(name);
  }
  
  @GetMapping("/string/{key}/{value}")
  public String saveStringToRedis(@PathVariable("key") String key,
                                  @PathVariable("value") String value){
    Jedis jedis=new Jedis( "localhost" );
    String s= jedis.get( key );
    Integer i=0;
    if(s!=null) {
      i = Integer.valueOf( s );
      
    }
    i++;
    jedis.set( key,String.valueOf( i ) );
    return "Info successfully created.";
  }
  @GetMapping("/list/{key}/{value}")
  public String saveListToRedis(@PathVariable("key") String key,
                                  @PathVariable("value") String value){
    Jedis jedis=new Jedis( "localhost" );
    jedis.lpush( key,value );
    return "Info successfully created.";
  }
  
  @GetMapping("/list/print/{key}")
  public Flux<List<String>> getAllKey(@PathVariable("key") String key){
    Jedis jedis=new Jedis( "localhost" );
    return Flux.just( jedis.lrange( key,0,-1 ) ).log()
    .delayElements( Duration.ofMillis( 1000 ) );
  }
  
  @GetMapping("/redis/multi")
  public String saveMultiThreds(){
    
    Jedis jedis=new Jedis( "localhost" );
    Transaction t=jedis.multi();
    int j=1;
    
    for (int i=0;i<1000;i++) {
      j++;
      t.set( "test",String.valueOf(j) );
    }
    t.exec();
    return "changed successfully.";
  }
  @PostMapping("/rabbitmq/send")
  public void sendMsg(@RequestBody RequestEvent requestEvent){
    
    requestEvent.setDate( new Date().toString() );
    
    personService.send(requestEvent);
  }
  @PostMapping("/rabbitmq/reqeustReply")
  public void send1Msg(@RequestBody RequestEvent requestEvent){
    
    requestEvent.setDate( new Date().toString() );
    
    personService.sendRequestAndReply(requestEvent);
  }
  
  @PostMapping("/rabbitmq/reqeustReplyAsync")
  public void requestAndReplyAsync(@RequestBody RequestEvent requestEvent){
    
    requestEvent.setDate( new Date().toString() );
    
    personService.requestAndReplyAsync(requestEvent);
  }
  @PostMapping("/rabbitmq/requestAndForget")
  public void reqeustAndForget(@RequestBody RequestEvent requestEvent){
    
    
    personService.sendAndForget(requestEvent);
  }
  @GetMapping("/testDatePersion")
  public List<String> getDate() throws RuntimeException{
    ULocale locale1 = new ULocale( "fa_IR@calendar=persian");
    List<String> date= new ArrayList<>();
    Calendar test=Calendar.getInstance(locale1.toLocale());

    DateFormat df = DateFormat.getDateInstance( DateFormat.FULL, locale1.toLocale());
  
    date.add( df.format( test ) );
    DateFormat df1 = DateFormat.getDateInstance( DateFormat.DATE_FIELD, locale1.toLocale());
    date.add( df1.format( test ) );
  
    DateFormat df2 = DateFormat.getDateInstance(  DateFormat.DAY_OF_YEAR_FIELD, locale1.toLocale());
    date.add( df2.format( test ) );
return date;
  }
}
