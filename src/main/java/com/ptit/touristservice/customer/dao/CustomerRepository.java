package com.ptit.touristservice.customer.dao;

import com.ptit.touristservice.auth.model.User;
import com.ptit.touristservice.customer.model.response.HeaderProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CustomerRepository extends JpaRepository<User,String> {
    @Query("select new com.ptit.touristservice.customer.model.response.HeaderProfile(u) from User  u " +
            "where u.id = ?1")
    HeaderProfile getHeaderProfile(String id);
}
