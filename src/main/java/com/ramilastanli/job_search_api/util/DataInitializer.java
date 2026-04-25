package com.ramilastanli.job_search_api.util;

import com.ramilastanli.job_search_api.entity.Job;
import com.ramilastanli.job_search_api.repository.JobRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final JobRepository jobRepository;

    @Override
    public void run(String... args) {
        if (jobRepository.count() == 0) {
            List<Job> jobs = List.of(
                    Job.builder().title("Java Developer").location("Baku").jobType("FULL_TIME").salary(2500.0).description("Spring Boot mütəxəssisi").build(),
                    Job.builder().title("Frontend Developer").location("Sumqayit").jobType("REMOTE").salary(1800.0).description("React və Redux bilikləri").build(),
                    Job.builder().title("Python Intern").location("Baku").jobType("INTERNSHIP").salary(500.0).description("Məlumat analizi").build(),
                    Job.builder().title("Senior Backend").location("Ganja").jobType("PART_TIME").salary(4000.0).description("Microservices təcrübəsi").build(),
                    Job.builder().title("DevOps Engineer").location("Remote").jobType("FULL_TIME").salary(3500.0).description("Docker və Kubernetes").build(),
                    Job.builder().title("Java Junior").location("Baku").jobType("FULL_TIME").salary(1000.0).description("Java 17 bilikləri").build()
            );
            jobRepository.saveAll(jobs);
            System.out.println(">> Test datası bazaya əlavə edildi.");
        }
    }
}