package com.main.ws;
import com.main.dto.Service1Dto;
import com.main.dto.UserDto;
import com.main.model.Book;
import com.main.services.BookService;
import com.main.services.Service1Impl;
import com.main.Service2Provider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import reactor.core.publisher.Flux;
import redis.clients.jedis.Jedis;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
/**
 * Created By: Ali Mohammadi
 * Date: 17 Oct, 2021
 */
@RestController
@RequestMapping("/service1")
public class Service1Controller {
  
  @Autowired
  Service2Provider service2Provider;
  @Autowired
  Service1Impl service1Impl;
  @Autowired
  BookService bookService;
  @Autowired
  RestTemplate restTemplate;
  @GetMapping("/getInfo")
  public ResponseEntity getInfo(){
    String s=service2Provider.getInfo();
    String str=
    LocalDateTime.now().toLocalTime() +"==> Now ,call service11===>"+s;
    System.out.println( str);
    return ResponseEntity.ok( str );
  }
  @GetMapping("/save")
  public String save(){
    service1Impl.saveTransaction( Service1Dto.builder().name( "service1" ).num( 3.2 ).build() );
    return "Request to be submit successfully.";
  }
  @PostMapping("/saveMongo")
  public UserDto saveRecordIntoMongoDB(@RequestBody UserDto userDto){
    return service1Impl.saveTrnToMongo( userDto );
  }
  @GetMapping("/user/{name}/{lastName}")
  public List<UserDto> findUserWithNameInMongo(@PathVariable("name") String name,
                                               @PathVariable("lastName") String lastName){
    return  service1Impl.findUserByNameInMongo( name,lastName );
  }
  
  @GetMapping("/user/{regex}")
  public List<UserDto> findUserWithRegex(@PathVariable("regex") String regex){
    return service1Impl.findUserByNamesInMongo( regex );
  }
  @GetMapping("/delusers/{name}")
  public String deleteUserById(@PathVariable("name") String name){
    service1Impl.deleteByIdInMongo( name );
    return "Delete of document successfully performed";
  }
  
  @GetMapping(value = "/reactive/flux",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
  public Flux<String> getString(){
    return Flux.just( "This is test1","This is test2","This is test3","This is test4",
                      "This is test5","This is test6","This is test7","This is test8"
    ).log().delayElements( Duration.ofMillis( 10000 ) );
  }
  
  @GetMapping("/redis/multi")
  public String saveRedis(){
    Jedis jedis=new Jedis( "localhost" );
    
    jedis.set( "test",String.valueOf( 12 ) );
    return "changed successfully created.";
  }
  
  @GetMapping("/books/id/{id}")
  public List<Book> getAllBooks(@PathVariable("id") Long flag){
    if(flag!=0)
      bookService.removeBooks();
    return bookService.getAllBook();
  }
  
  @GetMapping("/books/{id}")
  public Book findByBook(@PathVariable("id") Long id){
    return Optional.of(bookService.findBookById( id )).get().orElse( null );
  }
}
