package com.main.service3.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
/**
 * Created By: Ali Mohammadi
 * Date: 30 Oct, 2021
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "user")
public class User {
  @Id
  private String id;
  private String name;
  private String lastName;
  private String age;
  
}
