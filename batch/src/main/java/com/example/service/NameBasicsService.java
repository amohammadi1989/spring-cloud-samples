package com.example.service;
import com.example.dto.NameBasicsMongo;
import com.example.repository.NameBasicsReactiveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
  
    return null; //nameBasicsReactiveRepository.findAll();
  }
}
