package com.hammerpulse.user_service.config;

import com.hammerpulse.user_service.repository.UserRepo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;

//@Configuration
//@EnableJpaAuditing
//public class AuditConfig {
//    @Bean
//    public AuditorAware<String> auditorAware(UserRepo userRepository) {
//        return () -> Optional.ofNullable(
//                SecurityContextHolder.getContext().getAuthentication().getName());
//    }
//}