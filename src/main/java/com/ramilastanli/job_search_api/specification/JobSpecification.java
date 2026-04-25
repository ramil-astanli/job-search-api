package com.ramilastanli.job_search_api.specification;

import com.ramilastanli.job_search_api.entity.Job;
import org.springframework.data.jpa.domain.Specification;

public class JobSpecification {

    public static Specification<Job> hasLocation(String location) {
        return (root, query, cb) ->
                (location == null || location.isBlank()) ? cb.conjunction() : cb.equal(root.get("location"), location);
    }

    public static Specification<Job> hasJobType(String jobType) {
        return (root, query, cb) ->
                (jobType == null || jobType.isBlank()) ? cb.conjunction() : cb.equal(root.get("jobType"), jobType);
    }

    public static Specification<Job> searchByKeyword(String keyword) {
        return (root, query, cb) -> {
            if (keyword == null || keyword.isBlank()) {
                return cb.conjunction();
            }

            // PostgreSQL-də yaratdığımız fts_match funksiyasını çağırırıq
            return cb.isTrue(
                    cb.function("fts_match",
                            Boolean.class,
                            root.get("title"),
                            root.get("description"),
                            cb.literal(keyword)
                    )
            );
        };
    }
}
