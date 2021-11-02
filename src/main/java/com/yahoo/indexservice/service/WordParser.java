package com.yahoo.indexservice.service;

import java.util.Set;

public interface WordParser {

    Set<String> parse(String row);
}
