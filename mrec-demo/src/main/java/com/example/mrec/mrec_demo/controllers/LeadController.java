package com.example.mrec.mrec_demo.controllers;

import java.net.URI;
import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import com.example.mrec.mrec_demo.dtos.LeadDto;
import com.example.mrec.mrec_demo.dtos.ResponseDto;
import com.example.mrec.mrec_demo.services.LeadServiceImpl;
import com.example.mrec.mrec_demo.utils.ResponseMessageStrConsts;

@Validated
@RestController
@RequestMapping("/api/lead")
public class LeadController {
    private final Logger log = LoggerFactory.getLogger(LeadController.class);
    
    private LeadServiceImpl leadService;

    LeadController(LeadServiceImpl leadService){
        this.leadService = leadService;
    }

    /***
     * 
     * @param leadId
     * @return LeadDto if lead duplicate is found
     * else throws LeadNotFoundException
     * 
     */


    @GetMapping("/dedupe/{id}")
    public ResponseEntity<LeadDto> leadDedupeCheck(
        @PathVariable("id") String leadId
    ){
        LeadDto leadDto = leadService.getLead(leadId);

        return ResponseEntity.ok().body(leadDto);
    }
    
    /***
     * 
     * Creates a lead with unique leadId (mobile no)
     * checks for duplicate leadId before creating a new one
     * 
     * @param leadDto
     * @param webRequest
     * 
     * @return ResponseEntity as badRequest for failure and created for success
     */

    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createLead(
        @Validated @RequestBody  LeadDto leadDto,
        WebRequest webRequest){

        leadService.createLead(leadDto);

        String uriStr = webRequest.getDescription(false);

        URI uri = URI.create(uriStr);

        log.info(uriStr);
        
        
        ResponseDto responseDto = new ResponseDto();
        responseDto.setMessage(ResponseMessageStrConsts.leadCreateSuccess);
        responseDto.setDateTime(LocalDateTime.now());


        return ResponseEntity.created(uri).body(responseDto);

    }

    /**
     * Finds and returns lead for given leadId
     * 
     * @param leadId
     * @return ResponseEntity as badRequest for failure and ok for success
     */

    @GetMapping("/{id}")
    public ResponseEntity<LeadDto> getLead(
        @PathVariable("id") String leadId
    ){
        LeadDto leadDto = leadService.getLead(leadId);
      
        return ResponseEntity.ok().body(leadDto);
    }
}
