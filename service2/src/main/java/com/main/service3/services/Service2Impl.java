package com.main.service3.services;
import com.main.service3.repository.Service2Repository;
import com.main.service3.repository.UserRepository;
import com.main.service3.dto.Service2Dto;
import com.main.service3.dto.UserDto;
import com.main.service3.model.Service2Entity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
/**
 * Created By: Ali Mohammadi
 * Date: 26 Oct, 2021
 */
@Service
public class Service2Impl {
  
  private Service2Repository service2Repository;
  private UserRepository userRepository;
  private ModelMapper modelMapper;
  
  public Service2Impl(Service2Repository service2Repository, ModelMapper modelMapper,UserRepository userRepository) {
    this.service2Repository = service2Repository;
    this.modelMapper = modelMapper;
    this.userRepository=userRepository;
  }
  
  public void saveTransaction(Service2Dto service2Dto){
    Service2Entity entity= modelMapper.map( service2Dto, Service2Entity.class );
    service2Repository.save( entity);
  }
  public Flux<UserDto> getAllUsers(String name){
    return userRepository.findByName(name).log()
    .map( u->modelMapper.map(u,UserDto.class) );
  }
}
