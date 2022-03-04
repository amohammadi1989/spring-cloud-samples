package com.main.grpcserver.grpc.users;
import com.main.grpcserver.models.Users;
import com.main.grpcserver.services.Services;
import com.main.user.UserRequest;
import com.main.user.User;
import com.main.user.UserResponse;
import com.main.user.UserServiceGrpc;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.stream.Collectors;
/**
 * Created By: Ali Mohammadi
 * Date: 03 Nov, 2021
 */
@GrpcService
public class UserServices extends UserServiceGrpc.UserServiceImplBase {
  @Autowired
  Services services;
  @Autowired
  ModelMapper modelMapper;
  @Override
  public void getUsers(UserRequest request, StreamObserver<UserResponse> responseObserver) {
    
    List<User>  users= services
    .getAllUser(request.getName())
    .parallelStream()
    .map(a->mapToDto(a) )
    .collect( Collectors.toList());
    
    
    UserResponse  userResponse= UserResponse
    .newBuilder()
    .addAllUsers( users )
    .setCount( users.size() )
    .setPageCount( 1 ).build();
    
    responseObserver.onNext( userResponse );
    
    responseObserver.onCompleted();
  }
  
  @Override
  public void getUsersWithPage(UserRequest request, StreamObserver<UserResponse> responseObserver) {
    
    Long count=services.getCountUser( request.getName() );
    
    for (int i=1;i<count/10;i++){
      Pageable pageable = PageRequest.of( i, 10 );
      
  
      List<User>  users= services.getUserByNamePageble( request.getName(),pageable )
      .parallelStream()
      .map(a->mapToDto(a) )
      .collect( Collectors.toList());
  
  
      UserResponse  userResponse= UserResponse
      .newBuilder()
      .addAllUsers( users )
      .setCount( count.intValue() )
      .setPageCount( i ).build();
  
      responseObserver.onNext( userResponse );
  
  
      try {
           Thread.sleep( 1000 );
      } catch (Exception e) {
    
      } finally {
    
      }
  
  
    }
    responseObserver.onCompleted();
    
  }
  
  
  
  private User mapToDto(Users users){
    return User
    .newBuilder()
    .setId( users.getId() )
    .setName( users.getName() )
    .setLastName( users.getLastName() )
    .build();
  }
}
