package com.main.repository;

import com.main.model.NameBasicsMongo;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.integration.annotation.Aggregator;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Created By: Ali Mohammadi
 * Date: 30 Nov, 2021
 */
@Repository
public interface NameBasicsRepository extends MongoRepository<NameBasicsMongo,String> {
  @Query("{primaryName:{'$regex':'^?0'}}")
  List<NameBasicsMongo> findByPrimaryName(String name);
  
  @Aggregation(pipeline = {  "{'$lookup':{'from':'TitlePrincipals','localField':'nconst','foreignField':'nconst','as':'tp','pipeline':[{'$lookup':{'from':'TitleBasics','localField':'tconst','foreignField':'tconst','as':'tb'}}]}}","{$project:{'nconst':1,'primaryName':1,'_id':0,'primaryProfession':1,'knownForTitles':1,\"tp.tconst\":1,\"tp.job\":1,\"tp.category\":1,\"tp.tb.titleType\":1,\"tp.tb.primaryTitle\":1,\"tp.tb.originalTitle\":1}}","{'$match':{'primaryName':{$regex:'^?0'},\"tp\":{$exists:true,$not:{$size:0}}}}"})
  List<NameBasicsMongo> findNameBasicsWithAggregation(String name);
  
  @Aggregation(pipeline ={"{'$match':{'deathYear':{$exists:true,$ne:\"\"},'birthYear':{$exists:true,$ne:\"\"},'primaryName':{$regex:'^?0'}}}","{$addFields:{'birthYear1':{$convert:{input:'$birthYear',to:'int'}},'deathYear1':{$convert:{input:'$deathYear',to:'int'}}}}","{$project:{'nconst':1,'_id':1,'primaryName':1,'age':{$subtract:[\"$deathYear1\",\"$birthYear1\"]}}}"} )
  List<NameBasicsMongo> getAgeOfNameWithParameters(String name);
}
