package com.main.services;
import com.main.dto.Service1Dto;
import com.main.dto.UserDto;
import com.main.model.Service1Entity;
import com.main.doc.UserDoc;
import com.main.repository.Service1Repository;
import com.main.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
/**
 * Created By: Ali Mohammadi
 * Date: 26 Oct, 2021
 */

@Service
public class Service1Impl {
  
  private Service1Repository service2Repository;
  private ModelMapper modelMapper;
  private UserRepository userRepository;
  
  public Service1Impl(Service1Repository service2Repository, ModelMapper modelMapper,
                      UserRepository userRepository) {
    this.service2Repository = service2Repository;
    this.modelMapper = modelMapper;
    this.userRepository=userRepository;
  }
  
  public void saveTransaction(Service1Dto service2Dto){
    Service1Entity entity= modelMapper.map( service2Dto, Service1Entity.class );
    service2Repository.save( entity);
  }
  
  public UserDto saveTrnToMongo(UserDto userDto){
    UserDoc userDoc=modelMapper.map( userDto,UserDoc.class );
    return modelMapper.map(userRepository.save( userDoc ),UserDto.class);
  }
  public List<UserDto> findUserByNameInMongo(String name,String lastName){
    return userRepository.findByName( name,lastName )
    .parallelStream().map( n->modelMapper.map( n,UserDto.class ) )
    .collect( Collectors.toList());
  }
  
  public List<UserDto> findUserByNamesInMongo(String regex){
    return userRepository.findByNameLike( regex)
    .parallelStream().map( n->modelMapper.map( n,UserDto.class ) )
    .collect( Collectors.toList());
  }
  public void deleteByIdInMongo(String name){
    userRepository.deleteById( name );
  }
}
