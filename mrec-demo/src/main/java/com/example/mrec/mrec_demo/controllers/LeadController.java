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

import com.example.mrec.mrec_demo.dtos.ErrorResponseDto;
import com.example.mrec.mrec_demo.dtos.LeadDedupeDto;
import com.example.mrec.mrec_demo.dtos.LeadDto;
import com.example.mrec.mrec_demo.dtos.ResponseDto;
import com.example.mrec.mrec_demo.services.LeadServiceImpl;
import com.example.mrec.mrec_demo.utils.ResponseMessageStrConsts;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Pattern;

@Tag(
    name = "REST APIs related to Lead"
)
@Validated
@RestController
@RequestMapping(path = "/api/lead")
public class LeadController {
    private final Logger log = LoggerFactory.getLogger(LeadController.class);
    
    private LeadServiceImpl leadService;

    LeadController(LeadServiceImpl leadService){
        this.leadService = leadService;
    }

    /***
     * 
     * @param String
     * if lead exists
     * @return ResponseEntity<LeadDedupeDto> with true status
     *
     * else
     * @return ResponseEntity<LeadDedupeDto> with false status and msg as create a new lead
     * 
     */

    @Operation(
        summary = "Checks for duplicate lead",
        description = "For given mobile no. as lead id, it checks for duplicate lead"
    )
    @ApiResponses(
        {
            @ApiResponse(
                responseCode = "200",
                description = "HTTP status OK",
                content = @Content(
                    schema = @Schema(
                        implementation = LeadDedupeDto.class
                    )
                )
                    
            ),

            @ApiResponse(
                responseCode = "500",
                description = "HTTP status INTERNAL SERVER ERROR",
                content = @Content(
                    schema = @Schema(
                        implementation = ErrorResponseDto.class
                    )
                )
                    
            ),


        }
    )

    @GetMapping("/dedupe/{id}")
    public ResponseEntity<LeadDedupeDto> leadDedupeCheck(
       @Pattern (regexp = "^\\d{10}$", message = "Mobile no. should be 10 digit")
       @PathVariable("id") String leadId
    ){
        LeadDedupeDto leadDedupeDto = new LeadDedupeDto();

        boolean isLeadFound = leadService.isLeadExist(leadId);

        if(isLeadFound){
            leadDedupeDto.setLeadExist(true);
            leadDedupeDto.setMessage("Lead already exists for id "+leadId);

            return ResponseEntity.ok().body(leadDedupeDto);
        }
        
        leadDedupeDto.setLeadExist(false);
        leadDedupeDto.setMessage("Please create a new lead for "+leadId);


        return ResponseEntity.ok().body(leadDedupeDto);
    }
    
    /***
     * 
     * Creates a lead with unique leadId (mobile no)
     * checks for duplicate leadId before creating a new one
     * 
     * @param LeadDto
     * @param WebRequest
     * 
     * 
     * @return ResponseEntity<ResponseDto> 
     * 
     * 
     */

    @Operation(
        summary = "Creates a new lead",
        description = "Creates a new lead after checking for duplicate lead id"
    )
    @ApiResponses(
        {
            @ApiResponse(
                responseCode = "201",
                description = "HTTP status CREATED",
                content = @Content(
                    schema = @Schema(
                        implementation = ResponseDto.class
                    )
                )
            ),

            
            @ApiResponse(
                responseCode = "400",
                description = "HTTP status BAD REQUEST",
                content = @Content(
                    schema = @Schema(
                        implementation = ErrorResponseDto.class
                    )
                )
            ),

            @ApiResponse(
                responseCode = "500",
                description = "HTTP status INTERNAL SERVER ERROR",
                content = @Content(
                    schema = @Schema(
                        implementation = ErrorResponseDto.class
                    )
                )
                    
            ),

        }
    )

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
     * @param String
     * @return ResponseEntity<LeadDto>
     * 
     *  
     */

     
    @Operation(
        summary = "Fetches lead details",
        description = "Finds and returns lead for given lead id"
    )
    @ApiResponses(
        {
            @ApiResponse(
                responseCode = "200",
                description = "HTTP status OK",
                content = @Content(
                    schema = @Schema(
                        implementation = LeadDto.class
                    )
                )
            ),
            
            @ApiResponse(
                responseCode = "400",
                description = "HTTP status BAD REQUEST",
                content = @Content(
                    schema = @Schema(
                        implementation = ErrorResponseDto.class
                    )
                )
            ),

            @ApiResponse(
                responseCode = "500",
                description = "HTTP status INTERNAL SERVER ERROR",
                content = @Content(
                    schema = @Schema(
                        implementation = ErrorResponseDto.class
                    )
                )
                    
            )
        }
    )

    @GetMapping("/{id}")
    public ResponseEntity<LeadDto> getLead(
       @Pattern (regexp = "^\\d{10}$", message = "Mobile no. should be 10 digit")    
       @PathVariable("id") String leadId
    ){
        LeadDto leadDto = leadService.getLead(leadId);
      
        return ResponseEntity.ok().body(leadDto);
    }
}
