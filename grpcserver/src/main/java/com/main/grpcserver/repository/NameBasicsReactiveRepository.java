package com.main.grpcserver.repository;
import com.main.grpcserver.models.NameBasicsMongo;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;


/**
 * Created By: Ali Mohammadi
 * Date: 30 Nov, 2021
 */
@Repository
public interface NameBasicsReactiveRepository extends ReactiveMongoRepository<NameBasicsMongo,String> {
}
