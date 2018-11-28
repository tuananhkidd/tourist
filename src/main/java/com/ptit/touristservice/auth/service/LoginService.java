package com.ptit.touristservice.auth.service;

import com.ptit.touristservice.auth.dao.UserRespository;
import com.ptit.touristservice.auth.model.JWTToken;
import com.ptit.touristservice.auth.model.LoginResult;
import com.ptit.touristservice.auth.model.RegisterBody;
import com.ptit.touristservice.auth.model.User;
import com.ptit.touristservice.base.response.*;
import com.ptit.touristservice.constants.ApplicationConstant;
import com.ptit.touristservice.constants.HeaderConstant;
import com.ptit.touristservice.constants.ResponseConstant;
import com.ptit.touristservice.event.EmailVerificationEvent;
import com.ptit.touristservice.security.HttpPostRequestBuilder;
import com.ptit.touristservice.utils.Base64Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.WebRequest;

@Service
public class LoginService {
    @Autowired
    private UserRespository userRespository;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @Transactional(rollbackFor = Exception.class)
    public Response login(String username, String password) {
        User user = userRespository.findByUsername(username);
        if (user == null) {
            return new BadRequestResponse<>(ResponseConstant.ErrorMessage.ACCOUNT_NOT_EXIST);
        }

        if (!user.getActived()) {
            return new UnauthorizationResponse<>(ResponseConstant.ErrorMessage.ACCOUNT_NOT_VERIFIED);
        }

        LinkedMultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "password");
        body.add("username", username);
        body.add("password", password);

        JWTToken token = new HttpPostRequestBuilder(restTemplate)
                .withUrl(ApplicationConstant.LOCAL_HOST + "/oauth/token")
                .setContentType(MediaType.APPLICATION_FORM_URLENCODED)
                .addToHeader(HeaderConstant.AUTHORIZATION, HeaderConstant.AUTHORIZATION_VALUE_PREFIX + Base64Utils.encode("trusted-app:secret"))
                .setFormDataBody(body)
                .execute(JWTToken.class);
        LoginResult loginResult = new LoginResult(userRespository.getDataIDWithUsername(username), token.getAccess_token(),
                token.getRefresh_token(),
                token.getExpires_in());
        return new OkResponse(loginResult);
    }

    public Response register(RegisterBody registerBody, WebRequest webRequest) {
        if (userRespository.existsByUsername(registerBody.getUsername())) {
            return new ResourceExistResponse("Account exist");
        }
        User user = new User(registerBody);
        userRespository.save(user);
        String appUrl = webRequest.getContextPath();
        applicationEventPublisher.publishEvent(new EmailVerificationEvent(user, webRequest.getLocale(), appUrl));

        return new OkResponse(user.getUsername());
    }
}
