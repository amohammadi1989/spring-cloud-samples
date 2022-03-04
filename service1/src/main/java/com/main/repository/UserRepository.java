package com.main.repository;
import com.main.doc.UserDoc;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
/**
 * Created By: Ali Mohammadi
 * Date: 28 Oct, 2021
 */
public interface UserRepository extends MongoRepository<UserDoc,String> {
  @Query("{$and:[{ 'name' : ?0},{'lastName': ?1}]}")
  List<UserDoc> findByName(String name,String lastName);
  @Query("{ 'name' : ?0 }")
   void deleteByName(String name);
  @Query("{'name' : {$regex: ?0}}")
  List<UserDoc> findByNameLike(final String regx);
}
