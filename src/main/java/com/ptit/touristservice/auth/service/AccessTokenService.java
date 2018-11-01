package com.ptit.touristservice.auth.service;

import com.ptit.touristservice.auth.dao.UserRespository;
import com.ptit.touristservice.auth.model.JWTToken;
import com.ptit.touristservice.auth.model.JWTTokenPayload;
import com.ptit.touristservice.auth.model.TokenData;
import com.ptit.touristservice.auth.model.TokenGroup;
import com.ptit.touristservice.base.model.TokenExpirationDto;
import com.ptit.touristservice.base.response.OkResponse;
import com.ptit.touristservice.base.response.Response;
import com.ptit.touristservice.constants.ApplicationConstant;
import com.ptit.touristservice.constants.HeaderConstant;
import com.ptit.touristservice.security.HttpPostRequestBuilder;
import com.ptit.touristservice.utils.AccessTokenUtil;
import com.ptit.touristservice.utils.Base64Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Date;

@Service
public class AccessTokenService {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private UserRespository userRepository;


    public TokenData generateNewAccessToken(String refreshToken) {
        LinkedMultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "refresh_token");
        body.add("username", "trusted-app");
        body.add("refresh_token", refreshToken);

        JWTToken token = new HttpPostRequestBuilder(restTemplate)
                .withUrl(ApplicationConstant.HOST + "/oauth/token")
                .withProtocol(HttpPostRequestBuilder.HTTP)
                .addToHeader(HeaderConstant.AUTHORIZATION, HeaderConstant.AUTHORIZATION_VALUE_PREFIX + Base64Utils.encode("trusted-app:secret"))
                .setFormDataBody(body)
                .execute(JWTToken.class);

        return new TokenData(token.getAccess_token(),
                token.getRefresh_token(),
                token.getExpires_in());
    }

    public ResponseEntity refreshAccessToken(TokenGroup tokenGroup) throws IOException {
        ResponseEntity response;
        JWTTokenPayload jwtAccessTokenPayload = AccessTokenUtil
                .decodeJWTAccessTokenPayload(tokenGroup.getAccessToken());
        Date now = new Date();
        long nowInMillis = now.getTime();
        if (nowInMillis > jwtAccessTokenPayload.getExp() * 1000) {
            String refreshToken = tokenGroup.getRefreshToken();
            JWTTokenPayload jwtRefreshTokenPayload = AccessTokenUtil
                    .decodeJWTAccessTokenPayload(tokenGroup.getRefreshToken());
            if (nowInMillis > jwtRefreshTokenPayload.getExp() * 1000) {
                response = new ResponseEntity<>(new TokenExpirationDto(refreshToken), Response.prepareHeader(), HttpStatus.UNAUTHORIZED);
            } else {
                TokenData tokenData = generateNewAccessToken(refreshToken);
                tokenGroup.setAccessToken(tokenData.getAccessToken());
                tokenGroup.setRefreshToken(tokenData.getRefreshToken());
                response = new OkResponse(tokenGroup);
            }
        } else {
            response = new OkResponse(tokenGroup);
        }
        return response;
    }
}
