package com.main.model;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;



@Entity
@Table(name = "SERVICE1")
@Data
public class Service1Entity implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private Double num;
  private String name;
}
