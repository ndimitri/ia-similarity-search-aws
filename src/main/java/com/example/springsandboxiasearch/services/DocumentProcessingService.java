package com.example.springsandboxiasearch.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service orchestrateur pour le traitement des documents
 */
@Service
@RequiredArgsConstructor
public class DocumentProcessingService {

  
  private final EmbeddingsService embeddingsService;
  private final VectorStoreService vectorStoreService;

  /**
   * Traite un document : génère l'embedding et le stocke
   */
  public void processDocument(String id, String query) throws Exception {
    // 1. Générer l'embedding
    List<Float> embedding = embeddingsService.embed(query);

//    List<?> topK = vectorSearchService.search();

    // 2. Stocker dans S3 (ou mock)
    vectorStoreService.storeVector(id, query, embedding);

  }
}

