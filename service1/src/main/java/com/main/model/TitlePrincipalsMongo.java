package com.main.model;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
/**
 * Created By: Ali Mohammadi
 * Date: 30 Nov, 2021
 */
@Document(collection = "TitlePrincipals")
@NoArgsConstructor
@Data
public class TitlePrincipalsMongo {
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private String tconst;
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private Integer ordering;
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private String nconst;
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private String category;
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private String job;
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private String characters;
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private List<TitleBasicsMongo> tb;
  //org.springframework.data.mongodb.repository
  //org.springframework.data.mongodb.repository.Aggregation
}
