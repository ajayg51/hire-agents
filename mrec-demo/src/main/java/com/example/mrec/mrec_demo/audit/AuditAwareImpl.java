package com.example.mrec.mrec_demo.audit;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@Component("AuditAwareImpl")
@OpenAPIDefinition(
    info = @Info(
        title = "Mrec demo docuementation",
        description = "Mrec demo app REST API documentation",
        version = "v1",
        contact = @Contact(
            name = "Ajay Kumar Gond",
            email = "ajayg51.nitp@gmail.com"
        )
    )
)
public class AuditAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of("ADM");
    }
    
}
