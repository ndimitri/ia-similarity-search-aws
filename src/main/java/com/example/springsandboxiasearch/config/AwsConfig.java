package com.example.springsandboxiasearch.config;

import java.net.URI;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.bedrockruntime.BedrockRuntimeClient;
import software.amazon.awssdk.services.s3.S3Client;

@Configuration
//@Profile("!mock")
public class AwsConfig {

  @Value("${aws.endpoint}")
  private String endpoint;

  @Value("${aws.access-key-id}")
  private String accessKeyId;

  @Value("${aws.secret-access-key}")
  private String secretAccessKey;

  @Value("${aws.region}")
  private String region;

  // BedrockRuntimeClient for DataPlane operations (e.g., invoking Bedrock models)
  @Bean
  public BedrockRuntimeClient bedrockClient(){
    return BedrockRuntimeClient.builder()
        .endpointOverride(URI.create(endpoint))
        .region(Region.of(region))
        .credentialsProvider(StaticCredentialsProvider.create(
            AwsBasicCredentials.create(accessKeyId, secretAccessKey)
        ))
        .build();
  }

  // S3Client for interacting with Amazon S3 (e.g., storing/retrieving data)
  @Bean
  public S3Client s3Client() {
    return S3Client.builder()
        .endpointOverride(URI.create(endpoint))
        .region(Region.of(region))
        .credentialsProvider(StaticCredentialsProvider.create(
            AwsBasicCredentials.create(accessKeyId, secretAccessKey)
        ))
        .build();
  }


}
