package com.linsr.locationlib.model;

import java.io.Serializable;

/**
 * Description
 *
 * @author Linsr 2018/7/18 上午10:43
 */
public class Coordinate implements Serializable {

    private Double lat;
    private Double lng;

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }
}
