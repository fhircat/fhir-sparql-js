package org.example.fhirsparql.web.controller;

import org.example.fhirsparql.web.model.SparqlQuery;
import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/sparql")
public class SparqlController {
    @PostMapping(value="/search/query", consumes = {"text/plain", "application/sparql-query"})
    public String executeSearch(@RequestBody String query) {
        return "Echo query: " + query;
    }

    @PostMapping(value="/search", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String executeSearchWithQueryParam(@RequestBody MultiValueMap<String, String> formData) {
        return "Echo query: " + formData.get("query");
    }

    @GetMapping(value="/search", consumes = {"text/plain", "application/sparql-query"})
    public String executeQuery(@RequestParam  String query) {
        return "Echo query: " + query;
    }
}
