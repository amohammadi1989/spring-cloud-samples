package com.main.grpcserver.models;
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
public class NameBasicsMongo {
  private String nconst;
  private String primaryName;
  private String birthYear;
  private String deathYear;
  private List<String> primaryProfession;
  private List<String> knownForTitles;

}
