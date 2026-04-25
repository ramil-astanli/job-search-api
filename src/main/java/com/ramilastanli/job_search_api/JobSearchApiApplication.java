package com.ramilastanli.job_search_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class JobSearchApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(JobSearchApiApplication.class, args);
	}

}
