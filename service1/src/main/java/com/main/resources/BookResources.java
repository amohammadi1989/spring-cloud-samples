package com.main.resources;
import com.main.model.Book;
import com.main.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 * Created By: Ali Mohammadi
 * Date: 31 Oct, 2021
 */
@RestController
@RequestMapping("/rest/books")
public class BookResources {
 @Autowired
 private BookService bookService;
 @GetMapping("/all")
  public List<Book> getAllBooks() {
   return bookService.getAllBook();
 }
}
