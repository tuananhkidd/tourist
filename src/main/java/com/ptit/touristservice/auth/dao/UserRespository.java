package com.ptit.touristservice.auth.dao;

import com.ptit.touristservice.auth.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface UserRespository extends JpaRepository<User,String> {
    User findByUsernameAndPassword(String username, String password);

    User findByUsername(String username);

    @Query("select u.id from User u where u.username = ?1")
    String getDataIDWithUsername(String userName);
}
