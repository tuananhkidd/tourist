package com.ptit.touristservice.auth.controller;

import com.ptit.touristservice.auth.model.TokenGroup;
import com.ptit.touristservice.auth.service.AccessTokenService;
import com.ptit.touristservice.base.response.OkResponse;
import com.ptit.touristservice.base.response.Response;
import com.ptit.touristservice.base.response.ServerErrorResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Api(value = "Access Token", description = "Access Token")
@RequestMapping("/api/auth")
@CrossOrigin("**")
public class AccessTokenController {
    @Autowired
    private AccessTokenService accessTokenService;

    @ApiOperation(value = "Cấp lại access token", response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Cấp lại access token thành công"),
            @ApiResponse(code = 401, message = "Refresh token hết hạn"),
    }
    )
    @PostMapping("/accessToken")
    public Response getNewAccessToken(@RequestBody String refreshToken) {
        Response baseResponse;
        try {
            baseResponse = new OkResponse(accessTokenService.generateNewAccessToken(refreshToken));
        } catch (Exception e) {
            baseResponse = new ServerErrorResponse();
        }
        return baseResponse;
    }

    @ApiOperation(value = "Làm mới access token", response = Iterable.class,
            notes = "Nếu access token hết hạn - cấp access token mới dựa vào refresh token\nNếu refresh token hết hạn, trả về 401")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Làm mới access token thành công", response = OkResponse.class),
            @ApiResponse(code = 400, message = "Trường không hợp lệ"),
            @ApiResponse(code = 401, message = "Refresh token hết hạn")
    }
    )
    @PostMapping("/accessToken/refresh")
    public ResponseEntity refreshAccessToken(@Valid @RequestBody TokenGroup tokenGroup) {
        ResponseEntity response;
        try {
            response = accessTokenService.refreshAccessToken(tokenGroup);
        } catch (Exception e) {
            e.printStackTrace();
            response = new ServerErrorResponse();
        }
        return response;
    }
}
