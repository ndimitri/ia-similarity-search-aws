package com.example.springsandboxiasearch.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DocumentResponse {

  private String id;
  private String message;
  private boolean success;

}
