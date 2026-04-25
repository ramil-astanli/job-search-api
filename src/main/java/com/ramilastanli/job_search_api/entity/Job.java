package com.ramilastanli.job_search_api.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "jobs", indexes = {
        @Index(name = "idx_job_location", columnList = "location"),
        @Index(name = "idx_job_type", columnList = "job_type")
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Job extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "location")
    private String location;

    @Column(name = "job_type")
    private String jobType;

    private Double salary;
}
