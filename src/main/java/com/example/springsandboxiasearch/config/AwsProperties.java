// src/main/java/com/example/springsandboxiasearch/config/AwsProperties.java
package com.example.springsandboxiasearch.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "aws")
public class AwsProperties {
  private String s3Bucket;
  private String bedrockModelId = "amazon.titan-embed-text-v2:0";
  private String rerankingModelId = "cohere.rerank-v3.5:0";
}

