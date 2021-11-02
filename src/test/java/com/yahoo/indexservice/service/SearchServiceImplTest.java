package com.yahoo.indexservice.service;

import com.yahoo.indexservice.dao.DocumentRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@SpringBootTest
class SearchServiceImplTest {

    @Mock
    private DocumentRepository documentRepository;
    @InjectMocks
    private SearchServiceImpl searchService;

    @Test
    public void search() {
        Set<String> words = Set.of("lorem", "ipsum");
        when(documentRepository.search("lorem")).thenReturn(Set.of("http://docs.org/1.txt", "http://docs.org/2.txt"));
        when(documentRepository.search("ipsum")).thenReturn(Set.of("http://docs.org/2.txt", "http://docs.org/3.txt"));
        Set<String> expectedDocs = Set.of("http://docs.org/2.txt");
        Set<String> actualDocs = searchService.search(words);
        assertEquals(expectedDocs, actualDocs);
        verify(documentRepository).search("lorem");
        verify(documentRepository).search("ipsum");
    }

    @Test
    public void searchNull() {
        Set<String> actual = searchService.search(null);
        assertEquals(Collections.EMPTY_SET, actual);
        verifyNoInteractions(documentRepository);
    }

    @Test
    public void searchNotFound() {
        when(documentRepository.search("hello")).thenReturn(Collections.EMPTY_SET);
        Set<String> actual = searchService.search(Set.of("hello"));
        assertEquals(Collections.EMPTY_SET, actual);
        verify(documentRepository).search("hello");
    }
}