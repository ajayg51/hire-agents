package com.example.mrec.mrec_demo.exceptions.lead;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class LeadCreationException extends RuntimeException{
    
    private String message;
}
