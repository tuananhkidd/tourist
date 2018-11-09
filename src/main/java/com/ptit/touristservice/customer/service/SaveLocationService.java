package com.ptit.touristservice.customer.service;

import com.ptit.touristservice.auth.dao.UserRespository;
import com.ptit.touristservice.auth.model.User;
import com.ptit.touristservice.base.response.BadRequestResponse;
import com.ptit.touristservice.base.response.OkResponse;
import com.ptit.touristservice.base.response.Response;
import com.ptit.touristservice.customer.dao.SavedLocationRepository;
import com.ptit.touristservice.customer.model.data.SaveLocation;
import com.ptit.touristservice.customer.model.body.SaveLocationBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaveLocationService {
    @Autowired
    private UserRespository userRespository;
    @Autowired
    private SavedLocationRepository savedLocationRepository;

    public Response saveLocationService(String userID,
                                        SaveLocationBody saveLocationBody) {
        User user = userRespository.findOne(userID);
        if (user== null){
            return new BadRequestResponse<>("User not exist");
        }
        SaveLocation saveLocation = new SaveLocation(saveLocationBody,user);
        savedLocationRepository.save(saveLocation);
        return new OkResponse();
    }

    public Response deleteSavedLocation(String userID, String id) {
        //TODO
        return new OkResponse();
    }

    public Response getAllSavedLocation(String userID) {
        if(!userRespository.exists(userID)){
            return new BadRequestResponse<>("User not exist");
        }
        return new OkResponse(savedLocationRepository.getSavedLocationDtos(userID));
    }
}
