package com.main;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
@Service
@FeignClient(name="SERVER1")
public interface Service2Provider {
  @RequestMapping("/service2/getInfo")
  String getInfo();
}
