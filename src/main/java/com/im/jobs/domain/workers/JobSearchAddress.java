package com.im.jobs.domain.workers;

public class JobSearchAddress {

    private String unit;
    private Integer maxJobDistance;
    private String longitude;
    private String latitude;

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Integer getMaxJobDistance() {
        return maxJobDistance;
    }

    public void setMaxJobDistance(Integer maxJobDistance) {
        this.maxJobDistance = maxJobDistance;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }
}
