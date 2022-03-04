package com.example.repository;

import com.example.dto.TitlePrincipals;
import com.example.dto.TitlePrincipalsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**
 * Created By: Ali Mohammadi
 * Date: 30 Nov, 2021
 */
@Repository
public interface PersonRepository extends JpaRepository<TitlePrincipalsEntity,Long> {
}
