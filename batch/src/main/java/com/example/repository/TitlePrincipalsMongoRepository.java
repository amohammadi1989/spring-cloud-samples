package com.example.repository;

import com.example.dto.TitlePrincipalsEntity;
import com.example.dto.TitlePrincipalsMongo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
/**
 * Created By: Ali Mohammadi
 * Date: 30 Nov, 2021
 */
@Repository
public interface TitlePrincipalsMongoRepository extends MongoRepository<TitlePrincipalsMongo,String> {
}
