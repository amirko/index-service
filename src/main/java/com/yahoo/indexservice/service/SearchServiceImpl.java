package com.yahoo.indexservice.service;

import com.yahoo.indexservice.dao.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    private DocumentRepository documentRepository;

    @Override
    public Set<String> search(Set<String> words) {
        if(CollectionUtils.isEmpty(words)) {
            return Collections.EMPTY_SET;
        }
        words = words.stream().map(String::toLowerCase).collect(Collectors.toSet());
        Iterator<String> it = words.iterator();
        Set<String> res = new HashSet<>(documentRepository.search(it.next()));
        while(it.hasNext()) {
            res.retainAll(documentRepository.search(it.next()));
        }
        return res;
    }

}
