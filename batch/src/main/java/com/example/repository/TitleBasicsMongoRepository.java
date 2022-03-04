package com.example.repository;

import com.example.dto.TitleBasicsMongo;
import com.example.dto.TitlePrincipalsMongo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
/**
 * Created By: Ali Mohammadi
 * Date: 30 Nov, 2021
 */
@Repository
public interface TitleBasicsMongoRepository extends MongoRepository<TitleBasicsMongo,String> {
}
