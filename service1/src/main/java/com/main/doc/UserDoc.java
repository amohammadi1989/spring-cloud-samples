package com.main.doc;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
/**
 * Created By: Ali Mohammadi
 * Date: 28 Oct, 2021
 */
@Document(collection = "User")
@Builder
@Getter
@Setter
public class UserDoc {
  @Id
  private String id;
  private String name;
  private String lastName;
  private int age;
  
  public UserDoc(String id, String name, String lastName, int age) {
    this.id = id;
    this.name = name;
    this.lastName = lastName;
    this.age = age;
  }
  
}
