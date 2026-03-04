package com.example.springsandboxiasearch.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchResult {
  private String id;
  private String text;
  private double similarityScore; // score de similarité (0-1)
}

