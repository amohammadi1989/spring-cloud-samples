package com.main.model;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
/**
 * Created By: Ali Mohammadi
 * Date: 30 Nov, 2021
 */
@Document(collection = "TitleBasics")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TitleBasicsMongo {
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private String tconst;
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private String titleType;
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private String primaryTitle;
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private String originalTitle;
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private Boolean isAdult;
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private String startYear;
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private String endYear;
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private List<String> genres;

}
