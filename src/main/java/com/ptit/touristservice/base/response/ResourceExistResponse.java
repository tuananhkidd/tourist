package com.ptit.touristservice.base.response;

import com.ptit.touristservice.constants.ResponseConstant;
import org.springframework.http.HttpStatus;

public class ResourceExistResponse extends Response {
    public ResourceExistResponse() {
        super(HttpStatus.CONFLICT, ResponseConstant.ErrorMessage.RESOURCE_EXIST);
    }

    public ResourceExistResponse(String msg) {
        super(HttpStatus.CONFLICT, msg);
    }
}
