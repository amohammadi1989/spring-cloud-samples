package com.main.ws;
import com.main.model.NameBasicsMongo;
import com.main.model.Result;
import com.main.repository.NameBasicsRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.LookupOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.mapreduce.MapReduceOptions;
import org.springframework.data.mongodb.core.mapreduce.MapReduceResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.CriteriaDefinition;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
/**
 * Created By: Ali Mohammadi
 * Date: 10 Jan, 2022
 */
@RestController
@RequestMapping("/mongo")
public class MongoController {
  @Autowired
  MongoTemplate mongoTemplate;
  @Autowired
  private NameBasicsRepository nameBasicsRepository;
  @GetMapping("/namebasic/{name}")
  public NameBasicsMongo getNameBasicWithName(@PathVariable("name") String name){
    //Optional<NameBasicsMongo> basicsMongo= nameBasicsRepository.findById(id);
    Query dd= new Query();
    dd.addCriteria( Criteria.where( "primaryName" ).regex( "^"+name+"" ) );
    
    return  mongoTemplate.findOne( dd,NameBasicsMongo.class );
  }
  @GetMapping("/namebasic/pname/{pn}")
  public List<NameBasicsMongo> getNameBasicWithPrimaryName(@PathVariable("pn") String pn){

    List<NameBasicsMongo> mongoList=nameBasicsRepository.findByPrimaryName( pn );
    return mongoList;
  }
  @GetMapping("/info1/{pn}")
  public List<NameBasicsMongo> getNameBasics1(@PathVariable("pn") String pn){
    LookupOperation join=Aggregation.lookup("TitlePrincipals","nconst","nconst","tp");
    
    MatchOperation match=Aggregation.match( Criteria.where( "primaryName" ).regex( "^"+pn+"" ) );
    Aggregation news=Aggregation.newAggregation( join,match );
    AggregationResults<NameBasicsMongo> mongo=mongoTemplate.aggregate( news, "NameBasics",
                                                            NameBasicsMongo.class );
    
    return mongo.getMappedResults();
  }
  
  @GetMapping("/info2/{pn}")
  public List<NameBasicsMongo> getNameBasics2(@PathVariable("pn") String pn){
    List<NameBasicsMongo> mongoList=nameBasicsRepository.findNameBasicsWithAggregation( pn );
    return mongoList;
  }
  @GetMapping("/info3/{pn}")
  public List<NameBasicsMongo> getNameBasics3(@PathVariable("pn") String pn){
    return nameBasicsRepository.getAgeOfNameWithParameters( pn );
  }
  @GetMapping("/info3/mapreduce")
  
  public Object getMapReduce(){
    String map="function(){     if(this.primaryName.startsWith('Big',0)) emit(this.primaryName,1);}";
    String reduce="function(key,val){return  Array.sum(val)}";
    String col="NameBasicsMongo";
    Query query=new Query();
    query.addCriteria( Criteria.where( "primaryName" ).regex( "^Ali" ) );
    MapReduceResults<Object> obj=
    mongoTemplate.mapReduce(new Query(), col, map, reduce,Object.class );
    System.out.println(obj);
    return obj;
  }
  
}
