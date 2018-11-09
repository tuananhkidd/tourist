package com.ptit.touristservice.customer.model.data;


import com.ptit.touristservice.auth.model.User;
import com.ptit.touristservice.customer.model.body.SaveLocationBody;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "saved_location")
public class SaveLocation {
    @Id
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @GeneratedValue(generator = "uuid")
    private String id;
    private Double lat;
    private Double lon;
    private String address;

    @OneToOne
    @JoinColumn(name = "userID")
    private User user;

    public SaveLocation() {
    }

    public SaveLocation(SaveLocationBody saveLocationBody,User user) {
        setLat(saveLocationBody.getLat());
        setLon(saveLocationBody.getLon());
        setAddress(saveLocationBody.getAddress());
        setUser(user);
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
