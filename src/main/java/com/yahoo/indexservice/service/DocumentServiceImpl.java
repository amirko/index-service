package com.yahoo.indexservice.service;

import com.yahoo.indexservice.dao.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class DocumentServiceImpl implements DocumentService {

    @Autowired
    private DocumentIngestService documentIngestService;
    @Autowired
    private DocumentRepository documentRepository;

    @Override
    public void addDocument(String url) {
        Set<String> words = parseDocument(url);
        documentRepository.addDocument(url, words);
    }

    @Override
    public void deleteDocument(String url) {
        documentRepository.deleteDocument(url);
    }

    private Set<String> parseDocument(String url) {
        return documentIngestService.ingest(url);
    }

}
