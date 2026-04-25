package com.ramilastanli.job_search_api.service;

import com.ramilastanli.job_search_api.dto.JobDto;
import com.ramilastanli.job_search_api.specification.JobSpecification;
import com.ramilastanli.job_search_api.entity.Job;
import com.ramilastanli.job_search_api.repository.JobRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobService {

    private final JobRepository jobRepository;

    public Page<JobDto> searchJobs(String keyword, String location, String jobType, int page, int size) {

        int adjustedPage = (page > 0) ? page - 1 : 0;
        int maxPageSize = 50;
        int safeSize = (size > maxPageSize) ? maxPageSize : size;

        if(safeSize <= 0) safeSize = 10;
        Pageable pageable = PageRequest.of(adjustedPage, safeSize, Sort.by("createdAt").descending());

        Specification<Job> spec = Specification.where(JobSpecification.searchByKeyword(keyword))
                .and(JobSpecification.hasLocation(location))
                .and(JobSpecification.hasJobType(jobType));

        return jobRepository.findAll(spec, pageable).map(this :: convertToDto);
    }

    public Page<JobDto> fullTextSearch(String keyword, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return jobRepository.searchFullText(keyword,pageable).map(this :: convertToDto);
    }

    @Cacheable(value = "jobSuggestions", key = "#keyword")
    public List<String> getSuggestedTitles(String keyword) {
        System.out.println("Baza sorgusu icra olunur: " + keyword);
        return jobRepository.findDistinctTitlesByKeyword(keyword);
    }

    private JobDto convertToDto(Job job) {
        return JobDto.builder()
                .id(job.getId())
                .title(job.getTitle())
                .description(job.getDescription())
                .location(job.getLocation())
                .jobType(job.getJobType())
                .salary(job.getSalary())
                .createdAt(job.getCreatedAt())
                .build();
    }
}
