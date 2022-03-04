package com.example.dto;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import java.io.Serializable;
/**
 * Created By: Ali Mohammadi
 * Date: 30 Nov, 2021
 */
@Document(collection = "TitlePrincipals")
@NoArgsConstructor
@Data
public class TitlePrincipalsMongo {
  private String tconst;
  private Integer ordering;
  private String nconst;
  private String category;
  private String job;
  private String characters;
}
