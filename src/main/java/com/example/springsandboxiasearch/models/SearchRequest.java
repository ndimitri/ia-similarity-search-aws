package com.example.springsandboxiasearch.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchRequest {
  private String query;
  private int topK = 5; // nombre de résultats à retourner
}

