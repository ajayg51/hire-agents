package com.example.mrec.mrec_demo.services;

import java.time.LocalDateTime;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.example.mrec.mrec_demo.dtos.LeadDto;
import com.example.mrec.mrec_demo.entities.Lead;
import com.example.mrec.mrec_demo.exceptions.lead.DuplicateLeadFoundException;
import com.example.mrec.mrec_demo.exceptions.lead.LeadGlobalException;
import com.example.mrec.mrec_demo.exceptions.lead.LeadNotFoundException;
import com.example.mrec.mrec_demo.mappers.LeadMapper;
import com.example.mrec.mrec_demo.repositories.LeadRepository;
import com.example.mrec.mrec_demo.services.intf.LeadServiceIntf;
import com.example.mrec.mrec_demo.utils.ResponseMessageStrConsts;

@Service
public class LeadServiceImpl implements LeadServiceIntf{
    
    private final Logger log = LoggerFactory.getLogger(LeadServiceImpl.class);

    private final LeadRepository leadRepository;
    
    LeadServiceImpl(
        LeadRepository leadRepository
        ){
        this.leadRepository = leadRepository;
    
    }

    /***
     * @{inheritDoc}
     * 
     * throws DuplicateLeadFoundException if duplicate lead is found for 
     * given lead id
     * 
     * throws LeadGlobalException if any exception occurs while
     * creating the lead
     * 
     * Creates a new lead for given lead id
     * 
     */

    public void createLead(LeadDto leadDto) {

        Optional<Lead> lead = leadRepository.findById(leadDto.getLeadId());
        
        if(lead.isPresent()){
            log.info("Duplicate lead found");

            throw new DuplicateLeadFoundException("A lead with given lead id already exists");
        
        }else{
            try {
                
                log.info("Creating a new lead");

                Lead lead2 = LeadMapper.dtoToLead(leadDto);
                
                lead2.setCreatedAt(LocalDateTime.now());
        
                leadRepository.save(lead2);

                

            } catch (Exception e) {
                throw new LeadGlobalException(e.getMessage());
            }
        }

         
    }

    /***
     * @{inheritDoc}
     * 
     * finds and returns a lead for given leadId 
     * 
     */

    @Override
    public LeadDto getLead(String leadId) {
        Lead lead = leadRepository.findById(leadId)
            .orElseThrow(() -> new LeadNotFoundException(ResponseMessageStrConsts.leadNotFound + leadId));
        
        LeadDto leadDto = LeadMapper.leadToDto(lead);

        return leadDto;
    }
}
