package com.ptit.touristservice.auth.controller;

import com.ptit.touristservice.auth.service.EmailVerificationService;
import com.ptit.touristservice.base.BaseController;
import com.ptit.touristservice.base.response.OkResponse;
import com.ptit.touristservice.base.response.Response;
import com.ptit.touristservice.base.response.ServerErrorResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

@RestController
@Api(value = "Email Verification", description = "Email xác nhận tài khoản")
@RequestMapping("/api/auth")
@CrossOrigin("**")
public class EmailVerificationController extends BaseController {
    @Autowired
    private EmailVerificationService emailVerificationService;

    @ApiOperation(value = "Kích hoạt tài khoản", response = Iterable.class,
            notes = "token kích hoạt tài khoản hết hạn trong vòng 1 ngày")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Kích hoạt tài khoản thành công",response = OkResponse.class),
            @ApiResponse(code = 400, message = "Tài khoản đã được kích hoạt"),
            @ApiResponse(code = 401, message = "Token kích hoạt hết hạn")
    })
    @GetMapping("/registration-confirm/{token}")
    public Response confirmRegistration(@PathVariable("token") String token) {
        Response baseResponse;
        try {
            baseResponse = emailVerificationService.confirmEmailVerification(token);
        } catch (Exception e) {
            baseResponse = new ServerErrorResponse();
        }
        return baseResponse;
    }

    @ApiOperation(value = "Gửi lại email kích hoạt tài khoản", response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Gửi email thành công", response = OkResponse.class)
    })
    @PostMapping("/newVerificationEmail")
    public Response resendVerificationEmail(@RequestBody String email,
                                                WebRequest webRequest) {
        Response baseResponse;
        try {
            baseResponse = emailVerificationService.createEmailVerificationRequest(email, webRequest);
        } catch (Exception e) {
            baseResponse = new ServerErrorResponse();
        }
        return baseResponse;
    }
}
