package com.dcsuibian.dewey.controller;

import com.dcsuibian.dewey.entity.Document;
import com.dcsuibian.dewey.service.DocumentService;
import com.dcsuibian.dewey.vo.PageWrapper;
import com.dcsuibian.dewey.vo.ResponseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/newest-documents")
public class NewestDocumentController {
    private final DocumentService documentService;

    @Autowired
    public NewestDocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    @GetMapping
    public ResponseWrapper<PageWrapper<Document>> get(@RequestParam("pageNumber") int pageNumber, @RequestParam("pageSize") int pageSize) {
        PageWrapper<Document> page = documentService.getNewest(pageNumber, pageSize);
        return ResponseWrapper.success(page);
    }
}
