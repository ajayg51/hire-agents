package com.example.mrec.mrec_demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

import java.net.URI;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;

import com.example.mrec.mrec_demo.controllers.LeadController;
import com.example.mrec.mrec_demo.dtos.LeadDto;
import com.example.mrec.mrec_demo.dtos.ResponseDto;
import com.example.mrec.mrec_demo.services.LeadServiceImpl;
import com.example.mrec.mrec_demo.utils.ResponseMessageStrConsts;


public class LeadControllerTest {

    @InjectMocks
    private LeadController leadController;

    @Mock
    private LeadServiceImpl leadService;

    @Mock
    private WebRequest webRequest;

        @BeforeEach
        public void setup(){
            MockitoAnnotations.openMocks(this);
        }

    private final String baseURL = "http://localhost:8080/api/lead";
    
    @Test
    public void testCreateLeadSuccess(){
        
        final String uriStr = baseURL + "/create";  
        
        when(webRequest.getDescription(false)).thenReturn(uriStr);
        
        LeadDto leadDto = new LeadDto();

        ResponseEntity<ResponseDto>  response = 
            leadController.createLead(leadDto, webRequest);

        verify(leadService, times(1)).createLead(leadDto);
        assertEquals(CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(ResponseMessageStrConsts.leadCreateSuccess, response.getBody().getMessage());
        assertNotNull(response.getBody().getDateTime());
        assertEquals(URI.create(uriStr), response.getHeaders().getLocation());

    }

    @Test
    public void testGetLeadSuccess(){
        final String leadId = "9877899878";
        LeadDto leadDto  = new LeadDto();

        leadDto.setLeadId(leadId);
        leadDto.setName("Ajay Gond");

        when(leadService.getLead(leadId)).thenReturn(leadDto);
        
        ResponseEntity<LeadDto> response = 
            leadController.getLead(leadId);
        
        verify(leadService, times(1)).getLead(leadId);

        assertNotNull(response.getBody());
        assertEquals(OK, response.getStatusCode());
        assertEquals(leadId, response.getBody().getLeadId());
    }


}
