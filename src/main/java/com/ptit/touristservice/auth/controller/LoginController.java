package com.ptit.touristservice.auth.controller;

import com.ptit.touristservice.auth.model.User;
import com.ptit.touristservice.auth.service.LoginService;
import com.ptit.touristservice.base.BaseController;
import com.ptit.touristservice.base.response.Response;
import com.ptit.touristservice.constants.Constant;
import com.ptit.touristservice.constants.HeaderConstant;
import com.ptit.touristservice.constants.ResponseConstant;
import com.ptit.touristservice.utils.UserDecodeUtils;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(value = "Login", description = "Đăng nhập", position = 1)
@RequestMapping("/api/auth")
@CrossOrigin("*")
public class LoginController extends BaseController {
    @Autowired
    private LoginService loginService;

    @ApiOperation(value = "Đăng nhập dành cho Customer", response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Đăng nhập thành công"),
            @ApiResponse(code = 401, message = "Sai email hoặc mật khẩu"),
            @ApiResponse(code = 403, message = "Tài khoản chưa được kích hoạt")
    })
    @PostMapping("/customer/login")
    public Response loginAsCustomer(@ApiParam(name = "encodedString", value = "username+\":\"+password, lấy kết quả encode theo Base64, sau đó thêm \"Basic \" + kết quả")
                                         @RequestHeader(HeaderConstant.AUTHORIZATION) String encodedString) {
        Response response;
        try {
            User user = UserDecodeUtils.decodeFromAuthorizationHeader(encodedString);
            response = loginService.login(user.getUsername(), user.getPassword());
        } catch (Exception e) {
            e.printStackTrace();
            response = new Response(HttpStatus.UNAUTHORIZED, ResponseConstant.Vi.WRONG_EMAIL_OR_PASSWORD);
        }
        return response;
    }

}
