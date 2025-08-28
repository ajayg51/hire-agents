package com.example.mrec.mrec_demo.audit;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

@Component("AuditAwareImpl")
public class AuditAwareImpl implements AuditorAware {

    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of("ADM");
    }
    
}
