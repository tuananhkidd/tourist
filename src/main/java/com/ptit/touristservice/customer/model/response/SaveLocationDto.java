package com.ptit.touristservice.customer.model.response;

import io.swagger.annotations.ApiModel;

@ApiModel
public class SaveLocationDto {
    private String id;
    private Double lat;
    private Double lon;
    private String address;

    public SaveLocationDto() {
    }

    public SaveLocationDto(String id, Double lat, Double lon, String address) {
        this.id = id;
        this.lat = lat;
        this.lon = lon;
        this.address = address;
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
}
