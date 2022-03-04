package com.main.service3.dto;
import lombok.*;

import java.io.Serializable;
/**
 * Created By: Ali Mohammadi
 * Date: 26 Oct, 2021
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Service2Dto implements Serializable {
  private Long id;
  private Double num;
  private String name;
}
