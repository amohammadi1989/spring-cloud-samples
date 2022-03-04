package com.main.apigateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * Created By: Ali Mohammadi
 * Date: 18 Oct, 2021
 */
@RestController
@RequestMapping("/fallback")
public class FallbackController {
  @GetMapping("/service")
  public String getFallback(){
    return "Fallback call when call webservices.";
  }
}
