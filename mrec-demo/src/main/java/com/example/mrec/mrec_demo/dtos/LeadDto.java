package com.example.mrec.mrec_demo.dtos;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LeadDto {
    
    @Nonnull
    @Pattern (regexp = "^\\d{10}$", message = "Mobile no. should be 10 digit")
    private String leadId;

    private String admId;

    @Nonnull
    private String name;

    @Nonnull
    private String location;

    @Nonnull
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dob;

    @Nonnull
    private String source;

    private LocalDateTime createdAt;

    
    @Nonnull
    private String createdBy;

    
    private LocalDateTime updatedAt;

    private String updatedBy;
}
