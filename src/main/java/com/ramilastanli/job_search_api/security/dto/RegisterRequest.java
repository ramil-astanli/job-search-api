package com.ramilastanli.job_search_api.security.dto;

import com.ramilastanli.job_search_api.security.Role;

public record RegisterRequest(
        String username,
        String password,
        String email,
        Role role
) {}
