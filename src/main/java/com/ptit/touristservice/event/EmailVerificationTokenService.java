package com.ptit.touristservice.event;

import com.ptit.touristservice.auth.dao.EmailVerificationTokenRepository;
import com.ptit.touristservice.auth.model.EmailVerificationToken;
import com.ptit.touristservice.auth.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailVerificationTokenService {
    @Autowired
    private EmailVerificationTokenRepository emailVerificationTokenRepository;

    public EmailVerificationToken createEmailVerificationToken(User user) {
        EmailVerificationToken emailVerificationToken = new EmailVerificationToken(user);
        return emailVerificationTokenRepository.save(emailVerificationToken);
    }
}
