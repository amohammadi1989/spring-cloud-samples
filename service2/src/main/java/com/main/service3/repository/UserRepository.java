package com.main.service3.repository;
import com.main.service3.model.User;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import javax.transaction.Transactional;
/**
 * Created By: Ali Mohammadi
 * Date: 30 Oct, 2021
 */
@Repository
@Transactional
public interface UserRepository extends ReactiveMongoRepository<User,String> {
  @Query("{name: ?0}")
  Flux<User> findByName(String name);
}
