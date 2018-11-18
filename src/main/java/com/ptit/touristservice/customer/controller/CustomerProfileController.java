package com.ptit.touristservice.customer.controller;

import com.ptit.touristservice.base.BaseCustomerController;
import com.ptit.touristservice.base.response.BadRequestResponse;
import com.ptit.touristservice.base.response.Response;
import com.ptit.touristservice.customer.service.ProfileService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "Student Profile", description = "Hồ sơ của SV")
@RequestMapping("/api/students")
@CrossOrigin("*")
public class CustomerProfileController extends BaseCustomerController{
    @Autowired
    private ProfileService profileService;
    @GetMapping("/headerProfile")
    public Response getHeaderProfile() {
        Response baseResponse;
        try {
            String studentID = getAuthenticatedCustomerID();
            baseResponse = profileService.getHeaderProfile(studentID);
        } catch (Exception e) {
            baseResponse = new BadRequestResponse();
        }
        return baseResponse;
    }
}
