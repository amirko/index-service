package com.yahoo.indexservice.service;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Component
public class WordParserImpl implements WordParser {

    @Override
    public Set<String> parse(String row) {
        if(row == null || "".equals(row)) {
            return Collections.EMPTY_SET;
        }
        return new HashSet<>(Arrays.asList(row.split("\\W+")));
    }
}
