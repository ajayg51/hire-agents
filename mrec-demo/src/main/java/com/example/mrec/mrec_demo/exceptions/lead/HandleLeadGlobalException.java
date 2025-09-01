package com.example.mrec.mrec_demo.exceptions.lead;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.example.mrec.mrec_demo.dtos.ErrorResponseDto;

@RestControllerAdvice
public class HandleLeadGlobalException {

    Logger log = LoggerFactory.getLogger(HandleLeadGlobalException.class);

    /***
     * 
     * It gets triggered whenever there is exception thrown 
     * related to Lead Service class
     * 
     * @param LeadGlobalException
     * @return ResponseEntity<ErrorResponseDto>
     * 
     * 
     */


    
    @ExceptionHandler(LeadGlobalException.class)
    public ResponseEntity<ErrorResponseDto> handleLeadGlobalException(
        LeadGlobalException e,
        WebRequest webRequest
    ){
        ErrorResponseDto errorResponseDto = new ErrorResponseDto();

        errorResponseDto.setApiPath(webRequest.getDescription(false));
        errorResponseDto.setDateTime(LocalDateTime.now());
        errorResponseDto.setMessage(e.getMessage());

        log.error(errorResponseDto.toString());

        return ResponseEntity.internalServerError().body(errorResponseDto);

    }


    /***
     * 
     * It gets triggered whenever there is exception thrown 
     * related to LeadCreation process
     * 
     * @param LeadCreationException
     * @return ResponseEntity<ErrorResponseDto>
     * 
     * 
     */

    
    @ExceptionHandler(LeadCreationException.class)
    public ResponseEntity<ErrorResponseDto> handleLeadCreationException(
        LeadCreationException e, 
        WebRequest webRequest){

        ErrorResponseDto errorResponseDto = new ErrorResponseDto();

        errorResponseDto.setApiPath(webRequest.getDescription(false));
        errorResponseDto.setDateTime(LocalDateTime.now());
        errorResponseDto.setMessage(e.getMessage());

        log.error(errorResponseDto.toString());

        return ResponseEntity.badRequest().body(errorResponseDto);
    }

    

    /***
     * 
     * It gets triggered whenever there is exception thrown 
     * related to LeadCreation process when duplicate lead is found
     * 
     * @param DuplicateLeadFoundException
     * @return ResponseEntity<ErrorResponseDto>
     * 
     * 
     */

    
    @ExceptionHandler(DuplicateLeadFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleDuplicateLeadFoundException(
        DuplicateLeadFoundException e,
        WebRequest webRequest
    ){
        
        ErrorResponseDto errorResponseDto = new ErrorResponseDto();

        errorResponseDto.setApiPath(webRequest.getDescription(false));
        errorResponseDto.setDateTime(LocalDateTime.now());
        errorResponseDto.setMessage(e.getMessage());

        log.error(errorResponseDto.toString());

            
        return ResponseEntity.badRequest().body(errorResponseDto);
        
    }


    
    /***
     * 
     * It gets triggered whenever there is exception thrown while
     * looking for a lead id
     * 
     * @param LeadNotFoundException
     * @return ResponseEntity<ErrorResponseDto>
     * 
     * 
     */

   
    
    @ExceptionHandler(LeadNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleLeadNotFoundException(
        LeadNotFoundException e,
        WebRequest webRequest
    ){
        
        ErrorResponseDto errorResponseDto = new ErrorResponseDto();

        errorResponseDto.setApiPath(webRequest.getDescription(false));
        errorResponseDto.setDateTime(LocalDateTime.now());
        errorResponseDto.setMessage(e.getMessage());

        log.error(errorResponseDto.toString());

            
        return ResponseEntity.badRequest().body(errorResponseDto);
        
    }



}
