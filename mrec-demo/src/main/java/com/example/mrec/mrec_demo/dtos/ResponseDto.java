package com.example.mrec.mrec_demo.dtos;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ResponseDto {
    private String message;
    private LocalDateTime dateTime;
}
