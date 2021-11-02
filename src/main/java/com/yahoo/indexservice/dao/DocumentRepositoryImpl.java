package com.yahoo.indexservice.dao;

import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class DocumentRepositoryImpl implements DocumentRepository {

    private Map<String, Set<String>> docToWords = new HashMap<>();
    private Map<String, Set<String>> wordToDocs = new HashMap<>();

    @Override
    public void addDocument(String url, Set<String> words) {
        docToWords.put(url, words);
        words.forEach(word -> {
            wordToDocs.computeIfAbsent(word, s -> new HashSet<>());
            wordToDocs.get(word).add(url);
        });
    }

    @Override
    public void deleteDocument(String url) {
        if(url == null || "".equals(url)) {
            return;
        }
        Set<String> keys = docToWords.get(url);
        keys.forEach(key -> wordToDocs.get(key).remove(url));
        docToWords.remove(url);
    }

    @Override
    public Set<String> search(String word) {
        return Optional.ofNullable(wordToDocs.get(word)).orElseGet(() -> Collections.EMPTY_SET);
    }
}
