package com.example.mrec.mrec_demo.exceptions.lead;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class LeadGlobalException extends RuntimeException{
    private String message;
}
