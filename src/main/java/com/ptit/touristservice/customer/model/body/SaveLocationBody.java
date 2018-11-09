package com.ptit.touristservice.customer.model.body;

import io.swagger.annotations.ApiModel;

@ApiModel
public class SaveLocationBody {
    private Double lat;
    private Double lon;
    private String address;

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
}
