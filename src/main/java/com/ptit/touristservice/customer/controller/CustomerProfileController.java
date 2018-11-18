package com.ptit.touristservice.customer.controller;

import com.ptit.touristservice.base.BaseCustomerController;
import com.ptit.touristservice.base.response.OkResponse;
import com.ptit.touristservice.base.response.Response;
import com.ptit.touristservice.base.response.ServerErrorResponse;
import com.ptit.touristservice.customer.model.body.PersonalInfoBody;
import com.ptit.touristservice.customer.service.ProfileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Api(value = "Student Profile", description = "Hồ sơ của SV")
@RequestMapping("/api/customers")
@CrossOrigin("*")
public class CustomerProfileController extends BaseCustomerController {
    @Autowired
    private ProfileService profileService;

    @GetMapping("/headerProfile")
    public Response getHeaderProfile() {
        Response baseResponse;
        try {
            String studentID = getAuthenticatedCustomerID();
            baseResponse = profileService.getHeaderProfile(studentID);
        } catch (Exception e) {
            baseResponse = new ServerErrorResponse();
        }
        return baseResponse;
    }

    @GetMapping("/profile")
    public Response getProfile() {
        Response baseResponse;
        try {
            String studentID = getAuthenticatedCustomerID();
            baseResponse = profileService.getProfile(studentID);
        } catch (Exception e) {
            baseResponse = new ServerErrorResponse();
        }
        return baseResponse;
    }

    @ApiOperation(value = "Cập nhật Thông Tin Cá Nhân của SV", response = Iterable.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Cập nhật thành công", response = OkResponse.class),
    })
    @PutMapping("/personalInfo")
    public Response updatePersonalInfo(@Valid @RequestBody PersonalInfoBody studentPersonalInfoBody) {
        Response baseResponse;
        try {
            String customerID = getAuthenticatedCustomerID();
            return profileService.updatePersonalInfo(customerID, studentPersonalInfoBody);
        } catch (Exception e) {
            baseResponse = new ServerErrorResponse();
        }
        return baseResponse;
    }
}
