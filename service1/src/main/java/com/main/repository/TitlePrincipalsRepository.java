package com.main.repository;

import com.main.model.TitlePrincipalsMongo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
/**
 * Created By: Ali Mohammadi
 * Date: 30 Nov, 2021
 */
@Repository
public interface TitlePrincipalsRepository extends MongoRepository<TitlePrincipalsMongo,String> {
}
