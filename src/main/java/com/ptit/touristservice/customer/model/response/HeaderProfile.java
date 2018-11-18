package com.ptit.touristservice.customer.model.response;

import com.ptit.touristservice.auth.model.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class HeaderProfile {
    @ApiModelProperty(notes = "Url ảnh avatar, có thể NULL", position = 1)
    private String avatarUrl;
    @ApiModelProperty(notes = "Họ", position = 3)
    private String fullName;
     private String id;
    private String email;

    public HeaderProfile() {
    }


    public HeaderProfile(User u) {
        this.email = u.getUsername();
        this.avatarUrl = u.getAvatarUrl();
        this.fullName = u.getFullName();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

}
