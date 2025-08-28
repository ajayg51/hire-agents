package com.example.mrec.mrec_demo.dtos;

import lombok.Data;

@Data
public class LeadDedupeDto {
    private boolean isLeadExist;
    
    private String message;
}
