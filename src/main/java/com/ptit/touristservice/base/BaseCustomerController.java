package com.ptit.touristservice.base;

import com.ptit.touristservice.auth.dao.UserRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;

public abstract class BaseCustomerController extends BaseController {
    @Autowired
    private UserRespository userRepository;

    protected String getAuthenticatedCustomerID() {
        String userEmail = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        return userRepository.getDataIDWithUsername(userEmail);
    }
}
