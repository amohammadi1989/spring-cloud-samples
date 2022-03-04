package com.main.grpcserver.services;

import com.main.grpcserver.models.NameBasicsMongo;
import com.main.grpcserver.repository.NameBasicsReactiveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
/**
 * Created By: Ali Mohammadi
 * Date: 02 Dec, 2021
 */
@Service
public class NameBasicsService {
  @Autowired
  NameBasicsReactiveRepository nameBasicsReactiveRepository;
  
  public Flux<NameBasicsMongo> geNameBasicsMongos(){
  
    return nameBasicsReactiveRepository.findAll();
  }
}
