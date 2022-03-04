package com.main.model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
/**
 * Created By: Ali Mohammadi
 * Date: 31 Oct, 2021
 */
@Data@Builder
@Entity
@AllArgsConstructor
public class Book {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  
  public Book(String isn, String title, String publisher, String authors) {
    this.isn = isn;
    this.title = title;
    this.publisher = publisher;
    this.authors = authors;
  }
  
  public Book() {
  }
  
  private String isn;
  private String title;
  private String publisher;
  private String authors;
}
