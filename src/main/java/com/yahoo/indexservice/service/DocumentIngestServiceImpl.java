package com.yahoo.indexservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class DocumentIngestServiceImpl implements DocumentIngestService {

    @Autowired
    private WordParser wordParser;

    @Override
    public Set<String> ingest(String url) {
        if(url == null || "".equals(url)) {
            return Collections.EMPTY_SET;
        }
        try {
            URL addr = new URL(url);
            URLConnection urlConnection = addr.openConnection();
            try(BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()))) {
                Set<String> words = new HashSet<>();
                String row = in.readLine();
                while(row != null) {
                    words.addAll(wordParser.parse(row).stream().map(String::toLowerCase).collect(Collectors.toSet()));
                    row = in.readLine();
                }
                return words;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
