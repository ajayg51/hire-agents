package com.example.mrec.mrec_demo.mappers;

import com.example.mrec.mrec_demo.dtos.LeadDto;
import com.example.mrec.mrec_demo.entities.Lead;

public class LeadMapper {
    public static Lead  dtoToLead(LeadDto leadDto){
        Lead lead = new Lead();

        lead.setLeadId(leadDto.getLeadId());
        lead.setName(leadDto.getName());
        lead.setLocation(leadDto.getLocation());
        lead.setDob(leadDto.getDob());
        lead.setSource(leadDto.getSource());
        lead.setCreatedBy(leadDto.getCreatedBy());

        return lead;
    }

    public static LeadDto  leadToDto(Lead lead){
        LeadDto leadDto = new LeadDto();
        
        leadDto.setLeadId(lead.getLeadId());
        leadDto.setName(lead.getName());
        leadDto.setLocation(lead.getLocation());
        leadDto.setDob(lead.getDob());
        leadDto.setSource(lead.getSource());
        leadDto.setCreatedBy(lead.getCreatedBy());

        return leadDto;
    }


}
