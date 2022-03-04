package com.main.grpcserver.services;
import com.main.grpcserver.repository.UserRepository;
import com.main.grpcserver.models.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
@Service
public class Services {
  @Autowired
  private UserRepository userRepository;
  public List<Users> getAllUser(String name){
    return userRepository.findByName(name);
  }
  public Long getCountUser(String name){
    return userRepository.getCountByName(name);
  }
  public List<Users> getUserByNamePageble(String name,Pageable pageable){
    return userRepository.findUsersByNamePageable(name,pageable);
  }
  
  @PostConstruct
  public void init(){
    for(int i=0;i<=200;i++) {
      Users u = Users.builder().lastName( "A" + i ).name( "Ali" + i ).build();
      userRepository.save( u );
    }
    for(int i=0;i<=300;i++) {
      Users u = Users.builder().lastName( "A" + i ).name( "Reza" + i ).build();
      userRepository.save( u );
    }
  }
}
