package com.example.mrec.mrec_demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;

import com.example.mrec.mrec_demo.dtos.ErrorResponseDto;
import com.example.mrec.mrec_demo.exceptions.lead.HandleLeadGlobalException;
import com.example.mrec.mrec_demo.exceptions.lead.LeadGlobalException;

public class HandleLeadGlobalExceptionTest {
    
    @InjectMocks
    private HandleLeadGlobalException handleLeadGlobalException;

    @Mock
    private WebRequest webRequest;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testHandleLeadGlobalException(){
        final String errorMsg = "Lead related exception";
        final String uriPathStr = "uri=/api/lead";

        LeadGlobalException leadGlobalException = new LeadGlobalException(errorMsg);

        when(webRequest.getDescription(false)).thenReturn(uriPathStr);
        
        ResponseEntity<ErrorResponseDto> response = 
            handleLeadGlobalException.handleLeadGlobalException(leadGlobalException, webRequest);


        assertEquals(BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(errorMsg, response.getBody().getMessage());
        assertEquals(uriPathStr, response.getBody().getApiPath());
        assertNotNull(response.getBody().getDateTime());

        assertTrue(response.getBody().getDateTime().isBefore(LocalDateTime.now().plusSeconds(1)));

    }
    
}
