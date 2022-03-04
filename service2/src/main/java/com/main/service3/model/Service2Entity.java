package com.main.service3.model;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
/**
 * Created By: Ali Mohammadi
 * Date: 26 Oct, 2021
 */

@Entity
@Table(name = "SERVICE2")
@Data
public class Service2Entity implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private Double num;
  private String name;
}
