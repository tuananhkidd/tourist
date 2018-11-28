package com.ptit.touristservice.auth.dao;


import com.ptit.touristservice.auth.model.EmailVerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailVerificationTokenRepository extends JpaRepository<EmailVerificationToken, String> {
    EmailVerificationToken findByToken(String token);
}
