package com.main.service3.repository;
import com.main.service3.model.Service2Entity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**
 * Created By: Ali Mohammadi
 * Date: 26 Oct, 2021
 */
@Repository
public interface Service2Repository extends JpaRepository<Service2Entity,Long> {
}
