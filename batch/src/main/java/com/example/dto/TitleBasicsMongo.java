package com.example.dto;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
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
  private String tconst;
  private String titleType;
  private String primaryTitle;
  private String originalTitle;
  private Boolean isAdult;
  private String startYear;
  private String endYear;
  private List<String> genres;

}
