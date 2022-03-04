package com.example.repository;
import com.example.dto.NameBasicsMongo;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;


/**
 * Created By: Ali Mohammadi
 * Date: 30 Nov, 2021
 */
@Repository
public interface NameBasicsReactiveRepository extends MongoRepository<NameBasicsMongo,String> {
}
