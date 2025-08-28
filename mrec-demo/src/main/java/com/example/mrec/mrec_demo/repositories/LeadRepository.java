package com.example.mrec.mrec_demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.mrec.mrec_demo.entities.Lead;

public interface LeadRepository extends JpaRepository<Lead, String>{
    
}
