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
@Document(collection = "NameBasics")
@Data
@NoArgsConstructor

public class NameBasicsMongo  {
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private String nconst;
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private String primaryName;
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private String birthYear;
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private String deathYear;
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private List<TitlePrincipalsMongo> tp;
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private List<String> primaryProfession;
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private List<String> knownForTitles;
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private Integer age;

}
