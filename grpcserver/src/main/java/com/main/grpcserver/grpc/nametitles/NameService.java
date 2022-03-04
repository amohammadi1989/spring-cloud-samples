package com.main.grpcserver.grpc.nametitles;
import com.main.grpcserver.models.NameBasicsMongo;
import com.main.namebasic.BasicTitleServiceGrpc;
import com.main.namebasic.ItemName;
import com.main.namebasic.Request;
import com.main.namebasic.ResponseItem;
import com.main.grpcserver.services.NameBasicsService;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.stream.Collectors;
/**
 * Created By: Ali Mohammadi
 * Date: 02 Dec, 2021
 */
@GrpcService
public class NameService extends BasicTitleServiceGrpc.BasicTitleServiceImplBase {
  @Autowired
  NameBasicsService nameBasicsService;
  @Autowired
  ModelMapper modelMapper;
  @Override
  public void getTitleService(Request request, StreamObserver<ResponseItem> responseObserver) {
  
    Flux<NameBasicsMongo> nameBasicsMongoFlux=nameBasicsService.geNameBasicsMongos();
    List<ItemName> items= nameBasicsMongoFlux
    .toStream()
    .map( a->mapToDto( a )  )
    .collect( Collectors.toList());
  //items.stream().forEach( System.out::println );
    
    List<ItemName> itemNames=null;
    for(int i=1;i<items.size();i+=200){
      //System.out.println("items====>"+items.get( i ));
      ResponseItem response=
      ResponseItem.newBuilder().setCount( items.size() ).addAllItemName( items.subList( i,i+200 )).build();
      responseObserver.onNext( response );
  
      try {
        Thread.sleep( 1000 );
      } catch (Exception e) {
    
      } finally {
    
      }
  
    }

    responseObserver.onCompleted();
  }
  
  private ItemName mapToDto(NameBasicsMongo a) {
    return ItemName.newBuilder().setNconst( a.getNconst() )
    .setBirthYear( a.getBirthYear() )
    .setDeathYear( a.getDeathYear() )
    .setKnownForTitles( a.getKnownForTitles().toString() )
    .setPrimaryName( a.getPrimaryName() )
    .setPrimaryProfession( a.getPrimaryProfession().toString() )
    .build();
    
  }
}
