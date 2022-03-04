package com.main.person;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * Created By: Ali Mohammadi
 * Date: 04 Nov, 2021
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseEvent {
  private String msg;
  private String date;
}
