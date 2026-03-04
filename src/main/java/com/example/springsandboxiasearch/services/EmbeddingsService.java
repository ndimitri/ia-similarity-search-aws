package com.example.springsandboxiasearch.services;

import com.example.springsandboxiasearch.config.AwsProperties;
import java.util.ArrayList;
import java.util.List;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.SdkBytes;
import software.amazon.awssdk.services.bedrockruntime.BedrockRuntimeClient;
import software.amazon.awssdk.services.bedrockruntime.model.InvokeModelRequest;
import software.amazon.awssdk.services.bedrockruntime.model.InvokeModelResponse;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.node.ArrayNode;
import tools.jackson.databind.node.ObjectNode;

@Service
//@Profile("!mock")
@RequiredArgsConstructor
@Builder
public class EmbeddingsService {

  private final BedrockRuntimeClient bedrockClient;
  private final AwsProperties awsProperties;
  private final ObjectMapper objectMapper;

  public List<Float> embed(String text) throws Exception{
    ObjectNode payload = objectMapper.createObjectNode();
    payload.put("inputText", text);

    InvokeModelRequest request = InvokeModelRequest.builder()
        .modelId(awsProperties.getBedrockModelId())
        .contentType("application/json")
        .accept("application/json")
        .body(SdkBytes.fromUtf8String(objectMapper.writeValueAsString(payload)))
        .build();

    InvokeModelResponse response = bedrockClient.invokeModel(request);

    String json = response.body().asUtf8String();
    JsonNode node = objectMapper.readTree(json);

    ArrayNode array = (ArrayNode) node.get("embedding");

    List<Float> vector = new ArrayList<>();
    array.forEach(v -> vector.add(v.floatValue()));

    return vector;
  }


}
