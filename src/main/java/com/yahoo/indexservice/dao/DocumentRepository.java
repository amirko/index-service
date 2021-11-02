package com.yahoo.indexservice.dao;

import java.util.Set;

public interface DocumentRepository {

    void addDocument(String url, Set<String> words);
    void deleteDocument(String url);
    Set<String> search(String word);
}
