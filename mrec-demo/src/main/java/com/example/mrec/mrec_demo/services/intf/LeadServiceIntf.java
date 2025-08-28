package com.example.mrec.mrec_demo.services.intf;

import com.example.mrec.mrec_demo.dtos.LeadDto;

public interface LeadServiceIntf {
    /**
     * @param leadDto
     * 
     * Checks for a duplicate lead id and creates a new one
     * if not found
     * throws exception if duplicate lead id found
     * throws exception if any is thrown while creating the lead
     * 
     * @return void
     */


    public void createLead(LeadDto leadDto);
    
    /***
     * 
     * @param leadId
     * finds lead for given lead Id in DB and 
     * throws LeadNotFoundException if leadId is not found
     * 
     * @return LeadDto
     */
    
    public LeadDto getLead(String leadId);   

    /***
     * Checks for duplicate lead in DB for given leadId
     * @param leadId
     * @return true if lead exists else false
     */

    public boolean isLeadExist(String leadId);
}
