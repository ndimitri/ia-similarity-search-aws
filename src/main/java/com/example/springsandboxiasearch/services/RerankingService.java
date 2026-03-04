package com.example.springsandboxiasearch.services;

import com.example.springsandboxiasearch.config.AwsProperties;
import com.example.springsandboxiasearch.models.RerankedDocument;
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
import tools.jackson.databind.node.ObjectNode;

@Service
@RequiredArgsConstructor
public class RerankingService {
  private final BedrockRuntimeClient client;
  private final AwsProperties awsProperties;
  private final ObjectMapper mapper = new ObjectMapper();


  public List<RerankedDocument> rerank(String query, List<String> documents) throws Exception{
    ObjectNode payload = mapper.createObjectNode();
    payload.put("query", query);
    payload.set("documents", mapper.valueToTree(documents));

    InvokeModelRequest request = InvokeModelRequest.builder()
        .modelId(awsProperties.getRerankingModelId())
        .contentType("application/json")
        .accept("application/json")
        .body(SdkBytes.fromUtf8String(payload.toString()))
        .build();

    InvokeModelResponse response = client.invokeModel(request);

    JsonNode json = mapper.readTree(response.body().asUtf8String());
    ArrayNode results = (ArrayNode) json.get("results");

    List<RerankedDocument> output = new ArrayList<>();

    for(JsonNode r : results) {
      RerankedDocument doc = new RerankedDocument();
      doc.setIndex(r.get("index").asInt());
      doc.setRelevanceScore(r.get("relevance_score").asDouble());
      output.add(doc);
    }

    return output;

  }

}
