package com.example.mrec.mrec_demo.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="lead")
public class Lead {
    

    @Id
    private String leadId;

    private String admId;

    @Nonnull
    private String name;

    @Nonnull
    private String location;

    @Nonnull
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate dob;

    @Nonnull
    private String source;

    
    @Nonnull
    private LocalDateTime createdAt;

    
    @Nonnull
    private String createdBy;

    
    private LocalDateTime updatedAt;

    
    private String updatedBy;
}
