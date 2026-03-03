package com.example.springsandboxiasearch.services;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DocumentEmbeddingPipeline {

  private final BedrockEmbeddingsService embeddingsService;
  private final S3VectorStoreService vectorStore;

  public void processDocument(String id, String text) throws Exception{
    List<Float> embedding = embeddingsService.embed(text);
    vectorStore.storeVector(id, text, embedding);
  }

}
