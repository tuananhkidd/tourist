package com.ptit.touristservice.utils;

import com.ptit.touristservice.auth.model.User;
import com.ptit.touristservice.constants.HeaderConstant;

public class UserDecodeUtils {
    public static User decodeFromAuthorizationHeader(String headerValue) {
        headerValue = headerValue.replace(HeaderConstant.AUTHORIZATION_VALUE_PREFIX, "");
        String decodedValue = Base64Utils.decode(headerValue);
        String values[] = decodedValue.split(":");
        return new User(values[0], values[1]);
    }
}
