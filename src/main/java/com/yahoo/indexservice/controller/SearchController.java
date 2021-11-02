package com.yahoo.indexservice.controller;

import com.yahoo.indexservice.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("search")
public class SearchController {

    @Autowired
    private SearchService searchService;

    @GetMapping
    public Set<String> search(@RequestParam("words") String... words) {
        return searchService.search(Set.of(words));
    }
}
