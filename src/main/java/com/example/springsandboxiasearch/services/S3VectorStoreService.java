package com.example.springsandboxiasearch.services;

import java.util.List;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.node.ObjectNode;

@Service
public class S3VectorStoreService {
  private final S3Client s3;
  private final ObjectMapper mapper = new ObjectMapper();
  private final String bucket = "my-vector-bucket";

  public S3VectorStoreService(S3Client s3) {
    this.s3 = s3;
  }

  public void storeVector(String id, String text, List<Float> embedding) throws Exception {
    ObjectNode root = mapper.createObjectNode();
    root.put("id", id);
    root.put("text", text);
    root.set("embedding", mapper.valueToTree(embedding));

    String json = mapper.writeValueAsString(root);

    s3.putObject(
        PutObjectRequest.builder()
            .bucket(bucket)
            .key("vectors/" + id + ".json")
            .build(),
        RequestBody.fromString(json)
    );



  }

}
