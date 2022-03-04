package com.example.repository;
import com.example.dto.TitleBasicsMongo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
/**
 * Created By: Ali Mohammadi
 * Date: 30 Nov, 2021
 */
@Repository
public interface TitleBasicsRepository extends MongoRepository<TitleBasicsMongo,String> {
}
