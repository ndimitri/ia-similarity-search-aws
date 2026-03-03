package com.example.springsandboxiasearch.service;

import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.SdkBytes;
import software.amazon.awssdk.services.bedrockruntime.BedrockRuntimeClient;
import software.amazon.awssdk.services.bedrockruntime.model.InvokeModelRequest;
import software.amazon.awssdk.services.bedrockruntime.model.InvokeModelResponse;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.node.ArrayNode;

@Service
@RequiredArgsConstructor
public class BedrockEmbeddingsService {

  private final BedrockRuntimeClient bedrockClient;

  public List<Float> embed(String text){

    //Model ID for Amazon Titan Embed Text v2
    String modelId = "amazon.titan-embed-text-v2:0";

    InvokeModelRequest request = InvokeModelRequest.builder()
        .modelId(modelId)
        .contentType("application/json")
        .accept("application/json")
        .body(SdkBytes.fromUtf8String("{\"inputText\": \"" + text.replace("\"", "'") + "\"}"))
        .build();

    InvokeModelResponse response = bedrockClient.invokeModel(request);

    String json = response.body().asUtf8String();
    JsonNode node = new ObjectMapper().readTree(json);

    ArrayNode array = (ArrayNode) node.get("embedding");

    List<Float> vector = new ArrayList<>();
    array.forEach(v -> vector.add(v.floatValue()));

    return vector;
  }


}
