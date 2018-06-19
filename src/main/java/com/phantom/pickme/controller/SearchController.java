package com.phantom.pickme.controller;

import com.phantom.pickme.service.search.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SearchController {

    @Autowired
    private SearchService searchService;

    @GetMapping("/search/skills")
    public List<String> searchSkills(@RequestParam(name = "keyword", defaultValue = "") String keyword) {
        return searchService.searchSkills(keyword);
    }

    @GetMapping("/search/majors")
    public List<String> searchMajors(@RequestParam(name = "keyword", defaultValue = "") String keyword) {
        return searchService.searchMajors(keyword);
    }
}
