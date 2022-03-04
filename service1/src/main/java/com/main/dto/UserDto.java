package com.main.dto;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
/**
 * Created By: Ali Mohammadi
 * Date: 28 Oct, 2021
 */

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class UserDto {
  @Id
  private String id;
  private String name;
  private String lastName;
  private int age;
  
}
