package com.ptit.touristservice.customer.service;

import com.ptit.touristservice.auth.model.User;
import com.ptit.touristservice.base.response.BadRequestResponse;
import com.ptit.touristservice.base.response.ForbiddenResponse;
import com.ptit.touristservice.base.response.OkResponse;
import com.ptit.touristservice.base.response.Response;
import com.ptit.touristservice.constants.ResponseConstant;
import com.ptit.touristservice.customer.dao.CustomerRepository;
import com.ptit.touristservice.customer.model.body.PersonalInfoBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {
    @Autowired
    private CustomerRepository customerRepository;

    public Response getHeaderProfile(String id) {
        if (!customerRepository.exists(id)) {
            return new ForbiddenResponse(ResponseConstant.ErrorMessage.USER_IS_NOT_STUDENT);
        }
        return new OkResponse(customerRepository.getHeaderProfile(id));
    }

    public Response updatePersonalInfo(String customerID, PersonalInfoBody personalInfoBody) {
        User user = customerRepository.findOne(customerID);
        if (user == null) {
            return new BadRequestResponse<>(ResponseConstant.ErrorMessage.USER_NOT_EXIST);
        }

        user.update(personalInfoBody);
        user = customerRepository.save(user);
        return new OkResponse(user);
    }

    public Response getProfile(String studentID) {
        return new OkResponse(customerRepository.findOne(studentID));
    }
}
