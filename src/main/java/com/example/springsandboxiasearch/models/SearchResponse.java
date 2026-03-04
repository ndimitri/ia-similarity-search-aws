package com.example.springsandboxiasearch.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchResponse {
  private String query;
  private List<SearchResult> results;
  private int totalResults;
}

