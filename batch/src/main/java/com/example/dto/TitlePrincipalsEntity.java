package com.example.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
/**
 * Created By: Ali Mohammadi
 * Date: 30 Nov, 2021
 */
@Entity(name = "PrincipalsEntity")
@Table(name = "PrincipalsEntity")
@NoArgsConstructor
@Data
public class TitlePrincipalsEntity implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String tconst;
  private String ordering;
  private String nconst;
  private String category;
  private String job;
  private String characters;
}
