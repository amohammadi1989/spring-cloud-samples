package com.example.dto;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
/**
 * Created By: Ali Mohammadi
 * Date: 30 Nov, 2021
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TitleBasicsDto {
  private String tconst;
  private String titleType;
  private String primaryTitle;
  private String originalTitle;
  private Boolean isAdult;
  private String startYear;
  private String endYear;
  private String genres;

}
