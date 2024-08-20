package com.ibn.firnas.dto.airCrew;


public record LocationDTO(Long locationId,String ipAddress,String country,String time, String lat, String lng,Long userId) {
    public LocationDTO(Long locationId, String ipAddress, String country, String time, String lat, String lng,Long userId) {
        this.locationId = locationId;
        this.ipAddress = ipAddress;
        this.country = country;
        this.time = time;
        this.lat = lat;
        this.lng = lng;
        this.userId=userId;
    }
}
