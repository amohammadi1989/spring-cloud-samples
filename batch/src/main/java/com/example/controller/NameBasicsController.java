package com.example.controller;
import com.example.dto.NameBasicsMongo;
import com.example.service.NameBasicsService;
import com.mongodb.operation.GroupOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.LookupOperation;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.List;
/**
 * Created By: Ali Mohammadi
 * Date: 02 Dec, 2021
 */
@RestController
@RequestMapping("/v1")
public class NameBasicsController {
  @Autowired
  private NameBasicsService nameBasicsService;
  
  @GetMapping(value = "/getNameBasics",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
  public Flux<NameBasicsMongo> nameBasicsMongos(){
    return nameBasicsService.geNameBasicsMongos().delayElements( Duration.ofSeconds( 1000 ) );
  }
  public void test(){
    //LookupOperation groupOperation = Aggregation.lo
  /*
    // filtering same age count > 1
  
    MatchOperation matchOperation = Aggregation.match(new Criteria("count").gt(1));
  
  
    SortOperation sortOperation = Aggregation.sort(Sort.by(Sort.Direction.ASC, "count"));
  
    Aggregation aggregation = Aggregation.newAggregation(groupOperation, matchOperation, sortOperation);
  
    AggregationResults<AgeCount> result = mongoTemplate.aggregate(aggregation, "Employee", AgeCount.class);*/
  }
}
