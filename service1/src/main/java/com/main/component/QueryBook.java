package com.main.component;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.main.model.Book;
import com.main.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
/**
 * Created By: Ali Mohammadi
 * Date: 31 Oct, 2021
 */
@Component
public class QueryBook implements GraphQLQueryResolver {
  
  @Autowired
  BookService bookService;
  
  public List<Book> allBooks(Integer count){
    return bookService.getAllBook();
  }
  public Optional<Book> findBookId(Long id){
  return bookService.findById( id );
  }
}
