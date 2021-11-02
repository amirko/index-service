package com.yahoo.indexservice.controller;

import com.yahoo.indexservice.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping("document")
public class DocumentController {

    @Autowired
    private DocumentService documentService;

    @PostMapping
    // wasn't able to pass encoded url as a path variable
    public void addDocument(@PathParam("url") String url) {
        documentService.addDocument(url);
    }

    @DeleteMapping
    public void removeDocument(@PathParam("url") String url) {
        documentService.deleteDocument(url);
    }
}
