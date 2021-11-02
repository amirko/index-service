package com.yahoo.indexservice.service;

import java.util.Set;

public interface SearchService {

    Set<String> search(Set<String> words);
}
