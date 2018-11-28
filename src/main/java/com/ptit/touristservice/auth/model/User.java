package com.ptit.touristservice.auth.model;

import com.ptit.touristservice.customer.model.body.PersonalInfoBody;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @GeneratedValue(generator = "uuid")
    private String id;
    private String username;
    private String password;
    private String type = "EMAIL";
    private String fullName;
    private Date birthDay;
    private String avatarUrl;
    private boolean actived;

    public User(RegisterBody registerBody) {
        setUsername(registerBody.getUsername());
        setPassword(registerBody.getPassword());
        setFullName(registerBody.getFullName());
        setBirthDay(registerBody.getBirthDay() == -1 ? null:new Date(registerBody.getBirthDay()));
        setAvatarUrl(registerBody.getAvatarUrl());
        setActived(false);
    }

    public boolean getActived() {
        return actived;
    }

    public void setActived(boolean actived) {
        this.actived = actived;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        if (username != null && !username.isEmpty()) {
            this.username = username;
        }
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if (password != null && !password.isEmpty()) {
            this.password = password;
        }
    }

    public void update(PersonalInfoBody personalInfoBody) {
        setAvatarUrl(personalInfoBody.getAvatarUrl());
        setFullName(personalInfoBody.getFullName());
        setBirthDay(personalInfoBody.getBirthDay() == -1 ? null : new Date(personalInfoBody.getBirthDay()));
    }
}
