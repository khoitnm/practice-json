package org.tnmk.practicejson.pro01jsondynamictype;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class Pro01JsonDynamicTypeApplication {

  public static void main(String[] args) {
    SpringApplication.run(Pro01JsonDynamicTypeApplication.class, args);
  }
}
