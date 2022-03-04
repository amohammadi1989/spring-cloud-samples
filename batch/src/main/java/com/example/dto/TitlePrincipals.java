package com.example.dto;
import lombok.*;

import java.io.Serializable;
/**
 * Created By: Ali Mohammadi
 * Date: 29 Nov, 2021
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TitlePrincipals {
   private String tconst;
   private String ordering;
   private String nconst;
   private String category;
   private String job;
   private String characters;
}
