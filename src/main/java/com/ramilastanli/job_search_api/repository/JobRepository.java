package com.ramilastanli.job_search_api.repository;

import com.ramilastanli.job_search_api.entity.Job;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<Job, Long>, JpaSpecificationExecutor<Job> {

    @Query("SELECT DISTINCT j.title FROM Job j WHERE LOWER(j.title) LIKE LOWER(concat('%', :keyword, '%'))")
    List<String> findDistinctTitlesByKeyword(@Param("keyword") String keyword);

    @Query(value = "SELECT * FROM jobs " +
            "WHERE to_tsvector('english', coalesce(title, '') || ' ' || coalesce(description, '')) " +
            "@@ plainto_tsquery('english', :keyword) " +
            "ORDER BY ts_rank(to_tsvector('english', title), plainto_tsquery('english', :keyword)) DESC",
            countQuery = "SELECT count(*) FROM jobs " +
                    "WHERE to_tsvector('english', coalesce(title, '') || ' ' || coalesce(description, '')) " +
                    "@@ plainto_tsquery('english', :keyword)",
            nativeQuery = true)
    Page<Job> searchFullText(@Param("keyword") String keyword, Pageable pageable);
}