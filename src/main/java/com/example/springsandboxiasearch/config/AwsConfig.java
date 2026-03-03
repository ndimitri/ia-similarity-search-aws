package com.example.springsandboxiasearch.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.EnvironmentVariableCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.bedrockruntime.BedrockRuntimeClient;
import software.amazon.awssdk.services.s3.S3Client;

@Configuration
public class AwsConfig {

  // BedrockRuntimeClient for DataPlane operations (e.g., invoking Bedrock models)
  @Bean
  public BedrockRuntimeClient bedrockClient(){
    return BedrockRuntimeClient.builder()
        .region(Region.EU_WEST_3)
        .credentialsProvider(EnvironmentVariableCredentialsProvider.create())
        .build();
  }

  // S3Client for interacting with Amazon S3 (e.g., storing/retrieving data)
  @Bean
  public S3Client s3Client() {
    return S3Client.builder()
        .region(Region.EU_WEST_3)
        .credentialsProvider(EnvironmentVariableCredentialsProvider.create())
        .build();
  }


}
