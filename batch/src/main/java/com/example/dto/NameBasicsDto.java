package com.example.dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
/**
 * Created By: Ali Mohammadi
 * Date: 30 Nov, 2021
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NameBasicsDto {
  private String nconst;
  private String primaryName;
  private String birthYear;
  private String deathYear;
  private String primaryProfession;
  private String knownForTitles;

}
