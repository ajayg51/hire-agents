package com.example.mrec.mrec_demo.dtos;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ResponseExceptionDto {
    private String apiPath;
    private String message;
    private LocalDateTime dateTime;
}
