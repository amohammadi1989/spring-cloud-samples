package com.main.dto;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created By: Ali Mohammadi
 * Date: 26 Oct, 2021
 */

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Service1Dto implements Serializable {
  private Long id;
  private Double num;
  private String name;
}
