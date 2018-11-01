package com.ptit.touristservice.base.response;

import com.ptit.touristservice.constants.ResponseConstant;
import org.springframework.http.HttpStatus;

public class ServerErrorResponse extends Response {
    public <T> ServerErrorResponse() {
        super(HttpStatus.INTERNAL_SERVER_ERROR, ResponseConstant.ErrorMessage.INTERNAL_SERVER_ERROR);
    }
}
