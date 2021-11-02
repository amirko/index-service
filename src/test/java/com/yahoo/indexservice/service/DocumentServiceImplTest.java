package com.yahoo.indexservice.service;

import com.yahoo.indexservice.dao.DocumentRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class DocumentServiceImplTest {

    @Mock
    private DocumentIngestService documentIngestService;
    @Mock
    private DocumentRepository documentRepository;
    @InjectMocks
    private DocumentServiceImpl documentService;
    public static final String URL = "http://docs.org/doc.txt";

    @Test
    public void addDocument() {
        Set<String> words = Set.of("lorem", "ipsum");
        when(documentIngestService.ingest(URL)).thenReturn(words);
        documentService.addDocument(URL);
        verify(documentRepository).addDocument(URL, words);
    }

    @Test
    public void deleteDocument() {
        documentService.deleteDocument(URL);
        verify(documentRepository).deleteDocument(URL);
    }
}