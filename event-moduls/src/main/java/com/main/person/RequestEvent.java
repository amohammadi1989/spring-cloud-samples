package com.main.person;
import lombok.*;
/**
 * Created By: Ali Mohammadi
 * Date: 04 Nov, 2021
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RequestEvent {
  private String msg;
  private String date;
}
