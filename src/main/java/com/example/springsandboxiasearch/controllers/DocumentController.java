package com.example.springsandboxiasearch.controllers;

import com.example.springsandboxiasearch.models.DocumentRequest;
import com.example.springsandboxiasearch.models.DocumentResponse;
import com.example.springsandboxiasearch.services.DocumentProcessingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/documents")
@RequiredArgsConstructor
@Slf4j
public class DocumentController {

  private final DocumentProcessingService documentProcessingService;

  @PostMapping("/embed")
  public ResponseEntity<DocumentResponse> embedDocument(@RequestBody DocumentRequest request){
    try{
      if (request.getId() == null || request.getId().isBlank()) {
        return ResponseEntity.badRequest()
            .body(new DocumentResponse(null, "Document ID is required", false));
      }
      if (request.getText() == null || request.getText().isBlank()) {
        return ResponseEntity.badRequest()
            .body(new DocumentResponse(request.getId(), "Document text is required", false));
      }

      documentProcessingService.processDocument(request.getId(), request.getText());
      log.info("Document embedded successfully: {}", request.getId());

      return ResponseEntity.ok(
          new DocumentResponse(request.getId(), "Document embedded successfully", true));
    }
    catch (Exception e){
      log.error("Error embedding document: {}", request.getId(), e);
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body(new DocumentResponse(request.getId(), "Error: " + e.getMessage(), false));
    }
  }

}
