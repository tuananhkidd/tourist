package com.ptit.touristservice.base.response;

import com.ptit.touristservice.constants.ResponseConstant;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;

public class OkResponse extends Response {
    public <T> OkResponse(T data) {
        super(HttpStatus.OK, ResponseConstant.MSG_OK, data);
    }


    public OkResponse() {
        super(HttpStatus.OK, ResponseConstant.MSG_OK);
    }
}
