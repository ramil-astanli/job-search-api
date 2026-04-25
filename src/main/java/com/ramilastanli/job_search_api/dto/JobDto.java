package com.ramilastanli.job_search_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Builder;

import java.time.Instant;

@Builder
public record JobDto (
        Long id,

        @NotBlank(message = "Basliq bos ola bilmez")
        @Size(min = 3, max = 100, message = "Basliq 3-100 simvol araliginda olmalidir")
        String title,

        @NotBlank(message = "Tesvir bos ola bilmez")
        String description,

        @NotBlank(message = "Location bos ola bilmez")
        String location,

        @NotBlank(message = "Is novu bos ola bilmez")
        String jobType,

        @Positive(message = "Maas musbet olmalidir")
        Double salary,

        Instant createdAt
)
{}