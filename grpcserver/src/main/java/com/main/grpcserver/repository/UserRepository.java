package com.main.grpcserver.repository;
import com.main.grpcserver.models.Users;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
/**
 * Created By: Ali Mohammadi
 * Date: 03 Nov, 2021
 */
public interface UserRepository extends PagingAndSortingRepository<Users,Long> {
  @Query("select u from Users u where u.name like %:name% ")
  List<Users> findByName(@Param( "name" ) String name);
  @Query("select count (u) from Users u where u.name like %:name%")
  Long getCountByName(@Param( "name" ) String name);
  @Query("select u from Users u where u.name like %:name% ")
  List<Users> findUsersByNamePageable(@Param( "name" ) String name, Pageable pageable);
}
