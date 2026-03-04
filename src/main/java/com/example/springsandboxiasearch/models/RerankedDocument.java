package com.example.springsandboxiasearch.models;

import lombok.Data;

@Data
public class RerankedDocument {
  private int index;
  private double relevanceScore;

}
