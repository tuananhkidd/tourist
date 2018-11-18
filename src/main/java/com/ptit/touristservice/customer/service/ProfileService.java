package com.ptit.touristservice.customer.service;

import com.ptit.touristservice.base.response.ForbiddenResponse;
import com.ptit.touristservice.base.response.OkResponse;
import com.ptit.touristservice.base.response.Response;
import com.ptit.touristservice.constants.ResponseConstant;
import com.ptit.touristservice.customer.dao.CustomerRepository;
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
}
