package com.ptit.touristservice.utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ptit.touristservice.auth.model.JWTTokenPayload;

import java.io.IOException;

public class AccessTokenUtil {
    public static JWTTokenPayload decodeJWTAccessTokenPayload(String accessToken) throws IOException {
        String tokenPayloadBase64Encoded = accessToken.split("\\.")[1];
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
//        objectMapper.setVisibilityChecker(VisibilityChecker.Std.defaultInstance().withFieldVisibility(JsonAutoDetect.Visibility.ANY));
        return objectMapper.readValue(Base64Utils.decode(tokenPayloadBase64Encoded), JWTTokenPayload.class);
    }
}
