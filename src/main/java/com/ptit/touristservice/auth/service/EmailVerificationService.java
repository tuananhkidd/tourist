package com.ptit.touristservice.auth.service;

import com.ptit.touristservice.auth.dao.EmailVerificationTokenRepository;
import com.ptit.touristservice.auth.dao.UserRespository;
import com.ptit.touristservice.auth.model.EmailVerificationToken;
import com.ptit.touristservice.auth.model.User;
import com.ptit.touristservice.base.response.BadRequestResponse;
import com.ptit.touristservice.base.response.OkResponse;
import com.ptit.touristservice.base.response.Response;
import com.ptit.touristservice.base.response.UnauthorizationResponse;
import com.ptit.touristservice.event.EmailVerificationEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;

import java.util.Calendar;

@Service
public class EmailVerificationService {
    @Autowired
    private EmailVerificationTokenRepository emailVerificationTokenRepository;
    @Autowired
    private UserRespository userRepository;
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    public Response confirmEmailVerification(String token) {
        EmailVerificationToken emailVerificationToken = emailVerificationTokenRepository.findByToken(token);
        if (emailVerificationToken == null) {
            return new UnauthorizationResponse("Invalid request, your account verification token is expired");
        }

        User user = emailVerificationToken.getUser();
        if (user.getActived()) {
            return new BadRequestResponse("Invalid request, your account has been activated");
        }

        Calendar cal = Calendar.getInstance();
        if ((emailVerificationToken.getExpirationDate().getTime() - cal.getTime().getTime()) <= 0) {
            return new UnauthorizationResponse("Invalid request, your account verification token was expired");
        }

        user.setActived(true);
        userRepository.save(user);
        return new OkResponse("Your account was activated successfully, please return to login page");
    }

    public Response createEmailVerificationRequest(String email, WebRequest webRequest) {
        User user = userRepository.findByUsername(email);
        if (user != null) {
            String appUrl = webRequest.getContextPath();
            applicationEventPublisher.publishEvent(new EmailVerificationEvent(user, webRequest.getLocale(), appUrl));
        }
        return new OkResponse();
    }
}
