package com.main.repository;

import com.main.model.TitleBasicsMongo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
/**
 * Created By: Ali Mohammadi
 * Date: 30 Nov, 2021
 */
@Repository
public interface TitleBasicsRepository extends MongoRepository<TitleBasicsMongo,String> {
}
