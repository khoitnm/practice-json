package org.tnmk.practicejson.pro02dynamicmessageschemabe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class Pro02DynamicMessageSchemaBeApplication {

  public static void main(String[] args) {
    SpringApplication.run(Pro02DynamicMessageSchemaBeApplication.class, args);
  }
}
