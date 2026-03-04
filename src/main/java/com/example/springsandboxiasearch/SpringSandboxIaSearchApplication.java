package com.example.springsandboxiasearch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.autoconfigure.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class SpringSandboxIaSearchApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringSandboxIaSearchApplication.class, args);
  }

}
