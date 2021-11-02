package com.yahoo.indexservice.service;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class WordParserImplTest {

    private WordParserImpl wordParser = new WordParserImpl();

    @Test
    public void parse() {
        Set<String> expected = Set.of("This", "is", "a", "test");
        Set<String> actual = wordParser.parse("This is a test");
        assertEquals(expected, actual);
    }

    @Test
    public void parseNull() {
        Set<String> actual = wordParser.parse(null);
        assertEquals(Collections.EMPTY_SET, actual);
    }
}