package com.ramilastanli.job_search_api.controller;

import com.ramilastanli.job_search_api.dto.JobDto;
import com.ramilastanli.job_search_api.service.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/jobs")
@RequiredArgsConstructor
@Validated
public class JobController {

    private final JobService jobService;

    @GetMapping("/search")
    public ResponseEntity<Page<JobDto>> searchJobs(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String location,
            @RequestParam(required = false) String jobType,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Page<JobDto> results = jobService.searchJobs(keyword, location, jobType, page, size);

        return ResponseEntity.ok(results);
    }

    @GetMapping("/suggestions")
    public ResponseEntity<List<String>> getSuggestions(@RequestParam String query) {
        if(query.length() < 2) {
            return ResponseEntity.ok(Collections.emptyList());
        }
        return ResponseEntity.ok(jobService.getSuggestedTitles(query));
    }

    @GetMapping("/search/advanced")
    public ResponseEntity<Page<JobDto>> advancedSearch(
            @RequestParam String keyword,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {

        return ResponseEntity.ok(jobService.fullTextSearch(keyword, page, size));
    }
}
