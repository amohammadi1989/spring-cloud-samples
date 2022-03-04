package com.main.services;
import com.main.model.Book;
import com.main.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;
/**
 * Created By: Ali Mohammadi
 * Date: 31 Oct, 2021
 */
@Service
public class BookService {
  @Autowired
  private BookRepository bookRepository;
  
  @Cacheable(cacheNames = "books")
  public List<Book> getAllBook(){
    return bookRepository.findAll();
  }
  @CacheEvict(cacheNames = "books")
  public String removeBooks(){
    return "Operations successfully created.";
  }
  @Cacheable(cacheNames = "books",key = "#id",unless = "#result=null")
  public Optional<Book> findBookById(Long id){
    return bookRepository.findById(id);
  }
  
  
  public Optional<Book> findById(Long id){
    return bookRepository.findById( id );
  }
  @PostConstruct
  public void init(){
    bookRepository.save( new Book( "A","B","C","D" ) );
    bookRepository.save( new Book( "A1","B1","C1","D1" ) );
    bookRepository.save( new Book( "A2","B2","C2","D2" ) );
    bookRepository.save( new Book( "A3","B3","C3","D3" ) );
    bookRepository.save( new Book( "A4","B4","C4","D4" ) );
    bookRepository.save( new Book( "A5","B5","C5","D5" ) );
    bookRepository.save( new Book( "A6","B6","C6","D6" ) );
    
  }
  
}
