package com.ptit.touristservice.customer.controller;

import com.ptit.touristservice.base.BaseCustomerController;
import com.ptit.touristservice.base.response.OkResponse;
import com.ptit.touristservice.base.response.Response;
import com.ptit.touristservice.base.response.ServerErrorResponse;
import com.ptit.touristservice.customer.model.body.SaveLocationBody;
import com.ptit.touristservice.customer.service.SaveLocationService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(value = "Save Location", description = "Save Location")
@RequestMapping("/api/customers")
@CrossOrigin("**")
public class SaveLocationController extends BaseCustomerController{
    @Autowired
    private SaveLocationService saveLocationService;

    @PostMapping("/save_location")
    public Response createSavedLocation(@RequestBody SaveLocationBody saveLocationBody) {
        Response baseResponse;
        try {
            String userID = getAuthenticatedCustomerID();
            baseResponse = (saveLocationService.saveLocationService(userID,saveLocationBody));
        } catch (Exception e) {
            baseResponse = new ServerErrorResponse();
        }
        return baseResponse;
    }

    @DeleteMapping("/save_location/{id}")
    public Response deleteSavedLocation(@PathVariable("id") String id) {
        Response baseResponse;
        try {
            String userID = getAuthenticatedCustomerID();
            baseResponse = (saveLocationService.deleteSavedLocation(userID,id));
        } catch (Exception e) {
            baseResponse = new ServerErrorResponse();
        }
        return baseResponse;
    }

    @GetMapping("/save_location")
    public Response getSavedLocation() {
        Response baseResponse;
        try {
            String userID = getAuthenticatedCustomerID();
            baseResponse = (saveLocationService.getAllSavedLocation(userID));
        } catch (Exception e) {
            baseResponse = new ServerErrorResponse();
        }
        return baseResponse;
    }
}
