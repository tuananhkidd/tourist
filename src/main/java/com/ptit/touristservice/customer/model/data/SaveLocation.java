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
    private String formattedAddress;
    private String formattedPhoneNumber;
    private String name;
    private String placeId;
    private float rating;

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    @OneToOne
    @JoinColumn(name = "userID")
    private User user;

    public SaveLocation() {
    }

    public SaveLocation(SaveLocationBody saveLocationBody, User user) {
        setLat(saveLocationBody.getLat());
        setLon(saveLocationBody.getLon());
        setName(saveLocationBody.getName());
        setFormattedAddress(saveLocationBody.getFormattedAddress());
        setFormattedPhoneNumber(saveLocationBody.getFormattedPhoneNumber());
        setUser(user);
        setPlaceId(saveLocationBody.getPlaceId());
        setRating(saveLocationBody.getRating());
    }

    public String getFormattedAddress() {
        return formattedAddress;
    }

    public void setFormattedAddress(String formattedAddress) {
        this.formattedAddress = formattedAddress;
    }

    public String getFormattedPhoneNumber() {
        return formattedPhoneNumber;
    }

    public void setFormattedPhoneNumber(String formattedPhoneNumber) {
        this.formattedPhoneNumber = formattedPhoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
